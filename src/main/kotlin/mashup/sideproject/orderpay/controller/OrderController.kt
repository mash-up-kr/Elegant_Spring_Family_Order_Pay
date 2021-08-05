package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.infrastructure.OpLogger
import mashup.sideproject.orderpay.model.dto.order.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import mashup.sideproject.orderpay.service.OrderService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/order"], produces = [MediaType.APPLICATION_JSON_VALUE])
class OrderController(private val orderService: OrderService) {

    companion object : OpLogger

    @PostMapping
    fun order(@RequestBody orderRequestDto: OrderRequestDto): OrderResponseDto {
        return orderService.order(orderRequestDto)
    }

}
