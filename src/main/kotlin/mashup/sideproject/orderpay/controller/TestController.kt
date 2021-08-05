package mashup.sideproject.orderpay.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("/index")
    fun index(): String {
        return "index"
    }
}