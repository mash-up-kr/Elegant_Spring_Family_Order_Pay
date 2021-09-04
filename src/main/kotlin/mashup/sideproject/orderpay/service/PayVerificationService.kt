package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.model.dto.iamport.WebHookDto
import mashup.sideproject.orderpay.model.dto.iamport.payments.PaymentCancelRequestDto
import mashup.sideproject.orderpay.model.repository.OrderRepository
import mashup.sideproject.orderpay.service.iamport_requester.IamportRequester
import org.springframework.stereotype.Service

@Service
class PayVerificationService(
    private val iamportRequester: IamportRequester,
    private val orderRepository: OrderRepository
) {

    fun verifyTransaction(webHookDto: WebHookDto) {
        val order = orderRepository.findByMerchantUid(webHookDto.merchantUid) ?: run {
            iamportRequester.requestPaymentCancel(
                PaymentCancelRequestDto(
                    webHookDto.impUid,
                    webHookDto.merchantUid,
                    reason = "가맹점 결제 오류"
                )
            )
            throw RuntimeException()
        }

        val iamportResponse = iamportRequester.requestPayment(webHookDto.impUid)

        if (order.amount != iamportResponse.response?.amount)
            throw RuntimeException()


    }

}