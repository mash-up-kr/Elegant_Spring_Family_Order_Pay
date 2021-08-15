package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.model.dto.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.OrderResponseDto
import mashup.sideproject.orderpay.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping(value = ["/api/v1/order"])
@RestController
class OrderController(private val orderService: OrderService) {
    @PostMapping
    fun requestOrder(@RequestBody orderDto: OrderRequestDto?): ResponseEntity<OrderResponseDto?>? {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.acceptOrder(orderDto!!))
    }

    @GetMapping
    fun getOrder(@RequestParam merchantUid: String?): ResponseEntity<OrderResponseDto?>? {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrder(merchantUid!!))
    }
}