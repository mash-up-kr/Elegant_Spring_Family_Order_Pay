package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.NotFoundException
import mashup.sideproject.orderpay.model.dto.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.OrderResponseDto
import mashup.sideproject.orderpay.model.entity.Order
import mashup.sideproject.orderpay.model.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter

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
        val count = orderRepository.count()
        return "ORD-" +
                order.createdAt?.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString() +
                "-" + count.toString().padStart(6, '0')
    }

    private fun CharSequence.padStart(length: Int, padChar: Char = ' '): CharSequence {
        if (length < 0)
            throw IllegalArgumentException("Desired length $length is less than zero.")
        if (length <= this.length)
            return this.subSequence(0, this.length)

        val sb = StringBuilder(length)
        for (i in 1..(length - this.length))
            sb.append(padChar)
        sb.append(this)
        return sb
    }

    @Transactional(readOnly = true)
    fun getOrder(merchantUid: String): OrderResponseDto? {
        val order: Order = orderRepository.findByMerchantUid(merchantUid) ?: throw NotFoundException(ErrorCode.ORDER_NOT_FOUND)
        //ToDo: Mapstruct 작업
        return OrderResponseDto(
                createdAt = order.createdAt,
                merchantUid = order.merchantUid,
                optionIdList = order.optionIdList,
                productIdList = order.productIdList
        )
    }
}