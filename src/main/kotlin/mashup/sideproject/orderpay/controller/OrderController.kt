package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.model.dto.order.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import mashup.sideproject.orderpay.service.OrderService
import org.springframework.web.bind.annotation.*

@RequestMapping(value = ["/api/v1/order"])
@RestController
class OrderController(private val orderService: OrderService) {
    @PostMapping
    fun requestOrder(@RequestBody orderDto: OrderRequestDto): OrderResponseDto {
        return orderService.acceptOrder(orderDto)
    }

    @GetMapping
    fun getOrder(@RequestParam merchantUid: String): OrderResponseDto {
        return orderService.getOrder(merchantUid)
    }
}