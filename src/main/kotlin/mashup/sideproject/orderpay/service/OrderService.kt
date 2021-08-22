package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.model.dto.order.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import mashup.sideproject.orderpay.model.entity.Order
import mashup.sideproject.orderpay.model.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class OrderService(private val orderRepository: OrderRepository) {
    @Transactional
    fun acceptOrder(orderDto: OrderRequestDto): OrderResponseDto {
        val order = Order(
            optionIdList = orderDto.optionIdList,
            productIdList = orderDto.productIdList
        )
        order.merchantUid = createMerchantUid(order)
        orderRepository.save(order)
        //ToDo: Mapstruct 작업
        return OrderResponseDto(
            createdAt = order.createdAt,
            merchantUid = order.merchantUid,
            optionIdList = order.optionIdList,
            productIdList = order.productIdList
        )
    }

    private fun createMerchantUid(order: Order): String {
        return "ORD-${order.createdAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"))}-${UUID.randomUUID()}"
    }

    @Transactional(readOnly = true)
    fun getOrder(merchantUid: String): OrderResponseDto {
        val order: Order = orderRepository.findByMerchantUid(merchantUid) ?: throw OrderPayException(ErrorCode.ORDER_NOT_FOUND)
        //ToDo: Mapstruct 작업
        return OrderResponseDto(
            createdAt = order.createdAt,
            merchantUid = order.merchantUid,
            optionIdList = order.optionIdList,
            productIdList = order.productIdList
        )
    }
}