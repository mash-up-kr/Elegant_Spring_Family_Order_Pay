package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.model.dto.order.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import mashup.sideproject.orderpay.model.dto.order.OrderSubmitRequestDto
import mashup.sideproject.orderpay.service.OrderRedisService
import mashup.sideproject.orderpay.service.OrderService
import org.springframework.web.bind.annotation.*

@RequestMapping(value = ["/api/v1/order"])
@RestController
class OrderController(
    private val orderRedisService: OrderRedisService,
    private val orderService: OrderService
) {
    @PostMapping
    fun requestOrder(@RequestBody orderDto: OrderRequestDto): OrderResponseDto {
        return orderRedisService.acceptOrder(orderDto)
    }

    @GetMapping
    fun getOrder(@RequestParam merchantUid: String): OrderResponseDto {
        return orderRedisService.getOrder(merchantUid)
    }

    @PostMapping("/submit")
    fun submitOrder(@RequestBody submitDto: OrderSubmitRequestDto) {
        val orderRedisDto = orderRedisService.getOrder(submitDto.merchantUid)
        orderService.saveOrder(submitDto, orderRedisDto)
    }
}