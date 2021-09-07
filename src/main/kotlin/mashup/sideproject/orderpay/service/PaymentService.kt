package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.infrastructure.OpLogger
import mashup.sideproject.orderpay.model.dto.PaymentInfo
import mashup.sideproject.orderpay.model.dto.iamport.payments.CancelReason
import mashup.sideproject.orderpay.model.dto.iamport.payments.PaymentCancelRequestDto
import mashup.sideproject.orderpay.model.entity.Order
import mashup.sideproject.orderpay.model.enums.PayStatus
import mashup.sideproject.orderpay.model.repository.OrderRedisRepository
import mashup.sideproject.orderpay.model.repository.OrderRepository
import mashup.sideproject.orderpay.service.iamport_requester.IamportRequester
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class PaymentService(
    private val verifyTransactionSupporter: VerifyTransactionSupporter,
    private val iamportRequester: IamportRequester,
    private val orderRepository: OrderRepository,
    private val orderRedisRepository: OrderRedisRepository
) {

    companion object : OpLogger

    fun payment(accountId: Long, paymentInfo: PaymentInfo) {
        runCatching {
            val orderFromRedis = orderRedisRepository.findByMerchantUid(paymentInfo.merchantUid) ?: throw OrderPayException(ErrorCode.ORDER_NOT_FOUND)

            val iamportResponse = iamportRequester.requestPayment(paymentInfo.impUid)

            verifyTransactionSupporter.verifyTransaction(orderFromRedis, iamportResponse, paymentInfo)

            Order.of(orderFromRedis).let {
                it.paidAt = iamportResponse.response?.paidAt
                it.payStatus = PayStatus.PAID
                orderRepository.save(it)
            }

        }.onFailure { e ->
            when {
                e is OrderPayException && e.errorCode == ErrorCode.ORDER_NOT_FOUND -> {
                    cancelPayment(paymentInfo.impUid, paymentInfo.merchantUid, CancelReason.MERCHANT_ERROR)
                    log.error("bad payment request -> cancel payment paymentInfo:$paymentInfo", e)
                }
                else -> log.error("payment is failed -> paymentInfo:$paymentInfo", e)
            }
        }.also {
            orderRedisRepository.deleteByMerchantUid(paymentInfo.merchantUid)
        }

    }

    fun cancelPayment(impUid: String, merchantUid: String, cancelReason: CancelReason) {
        iamportRequester.requestPaymentCancel(
            PaymentCancelRequestDto(impUid, merchantUid, reason = cancelReason.description)
        )
    }
}