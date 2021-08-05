package mashup.sideproject.orderpay.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/order")
class OrderViewController {

    @GetMapping
    fun order(elegantAccountId: Long): String {
        return "page/order-sheet"
    }
}