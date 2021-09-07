package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.model.dto.iamport.WebHookDto
import mashup.sideproject.orderpay.service.PaymentService
import mashup.sideproject.orderpay.service.VerifyTransactionSupporter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhookController(private val paymentService: PaymentService) {

    @PostMapping("/iamport/webhook")
    fun callback(accountId: Long, webHookDto: WebHookDto) {
        paymentService.payment(accountId, webHookDto)
    }
}