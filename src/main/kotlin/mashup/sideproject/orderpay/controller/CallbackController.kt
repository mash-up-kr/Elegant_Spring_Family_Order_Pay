package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.model.dto.iamport.CallbackDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CallbackController {

    @PostMapping("/iamport/callback")
    fun callback(callbackDto: CallbackDto) {

    }
}