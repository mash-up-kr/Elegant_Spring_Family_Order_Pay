package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import mashup.sideproject.orderpay.model.dto.order.OrderSubmitRequestDto
import mashup.sideproject.orderpay.model.mapper.OrderMapper
import mashup.sideproject.orderpay.model.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderMapper: OrderMapper,
    private val orderRepository: OrderRepository
) {
    @Transactional
    fun saveOrder(orderSubmitRequestDto: OrderSubmitRequestDto, orderResponseDto: OrderResponseDto) {
        val order = orderMapper.toOrder(orderSubmitRequestDto, orderResponseDto)
        orderRepository.save(order)
    }
}