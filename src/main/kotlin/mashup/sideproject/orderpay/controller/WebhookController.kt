package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.model.dto.iamport.WebHookDto
import mashup.sideproject.orderpay.service.VerifyTransactionSupporter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhookController(
    private val verifyTransactionSupporter: VerifyTransactionSupporter
) {

    @PostMapping("/iamport/webhook")
    fun callback(webHookDto: WebHookDto) {
        verifyTransactionSupporter.verifyTransaction(webHookDto)
    }
}