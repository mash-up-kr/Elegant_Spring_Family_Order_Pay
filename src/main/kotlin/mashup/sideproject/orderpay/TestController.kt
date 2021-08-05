package mashup.sideproject.orderpay

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController{
    @GetMapping("/ping")
    fun ping(): String {
        return "pongv1"
    }
}