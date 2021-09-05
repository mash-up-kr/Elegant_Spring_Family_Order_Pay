package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.model.dto.iamport.WebHookDto
import mashup.sideproject.orderpay.model.dto.iamport.payments.PaymentCancelRequestDto
import mashup.sideproject.orderpay.model.repository.OrderRedisRepository
import mashup.sideproject.orderpay.model.repository.OrderRepository
import mashup.sideproject.orderpay.service.iamport_requester.IamportRequester
import org.springframework.stereotype.Component

@Component
class VerifyTransactionSupporter(
    private val iamportRequester: IamportRequester,
    private val orderRedisRepository: OrderRedisRepository,
    private val orderRepository: OrderRepository
) {

    fun verifyTransaction(webHookDto: WebHookDto) {
        iamportRequester.requestPaymentCancel(
            PaymentCancelRequestDto(
                webHookDto.impUid,
                webHookDto.merchantUid,
                reason = "가맹점 결제 오류"
            )
        )

        // 기 결제된 경우
        orderRepository.findByMerchantUid(webHookDto.merchantUid)?.let {
            throw OrderPayException(ErrorCode.ALREADY_PAID_ORDER)
        }

        val orderFromRedis = orderRedisRepository.findByMerchantUid(webHookDto.merchantUid) ?: throw OrderPayException(ErrorCode.ORDER_NOT_FOUND)
        val iamportResponse = iamportRequester.requestPayment(webHookDto.impUid)

        // 결제 금액 불일치
        if (orderFromRedis.amount != iamportResponse.response?.amount)
            throw OrderPayException(ErrorCode.FORGED_PAYMENT, "forged request -> serverAmount:${orderFromRedis.amount}, iamportAmount:${iamportResponse.response?.amount}")

    }

}