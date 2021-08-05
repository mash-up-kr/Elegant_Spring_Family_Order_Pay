package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.model.dto.order.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService {
    fun order(orderRequestDto: OrderRequestDto): OrderResponseDto {
        return OrderResponseDto(UUID.randomUUID().toString())
    }
}