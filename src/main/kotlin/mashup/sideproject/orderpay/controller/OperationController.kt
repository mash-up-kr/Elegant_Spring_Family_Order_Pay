package mashup.sideproject.orderpay.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OperationController{
    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }
}