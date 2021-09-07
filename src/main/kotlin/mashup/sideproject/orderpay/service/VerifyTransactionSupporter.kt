package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.model.dto.PaymentInfo
import mashup.sideproject.orderpay.model.dto.iamport.IamportResponse
import mashup.sideproject.orderpay.model.dto.iamport.payments.PaymentAnnotation
import mashup.sideproject.orderpay.model.entity.OrderRedis
import mashup.sideproject.orderpay.model.enums.PayStatus
import mashup.sideproject.orderpay.model.repository.OrderRedisRepository
import mashup.sideproject.orderpay.model.repository.OrderRepository
import mashup.sideproject.orderpay.service.iamport_requester.IamportRequester
import org.springframework.stereotype.Component

@Component
class VerifyTransactionSupporter {

    fun verifyTransaction(order: OrderRedis, iamportResponse: IamportResponse<PaymentAnnotation>, paymentInfo: PaymentInfo) {

        // 기 처리된 건
        if (order.payStatus != PayStatus.READY)
            throw OrderPayException(ErrorCode.ALREADY_PROCESSED_ORDER)

        // 결제 금액 불일치
        if (order.amount != iamportResponse.response?.amount)
            throw OrderPayException(ErrorCode.FORGED_PAYMENT, "forged request -> serverAmount:${order.amount}, iamportAmount:${iamportResponse.response?.amount}")

    }

}