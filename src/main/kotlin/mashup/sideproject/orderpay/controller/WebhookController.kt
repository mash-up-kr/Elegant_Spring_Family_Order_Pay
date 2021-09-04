package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.model.dto.iamport.WebHookDto
import mashup.sideproject.orderpay.service.PayVerificationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhookController(
    private val payVerificationService: PayVerificationService
) {

    @PostMapping("/iamport/callback")
    fun callback(webHookDto: WebHookDto) {
        payVerificationService.verifyTransaction(webHookDto)
    }
}