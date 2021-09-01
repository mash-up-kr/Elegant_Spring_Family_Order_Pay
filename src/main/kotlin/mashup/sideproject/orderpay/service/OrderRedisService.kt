package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.model.dto.order.OrderRequestDto
import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import mashup.sideproject.orderpay.model.entity.OrderRedis
import mashup.sideproject.orderpay.model.repository.OrderRedisRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class OrderRedisService(private val orderRedisRepository: OrderRedisRepository) {
    @Transactional
    fun acceptOrder(orderDto: OrderRequestDto): OrderResponseDto {
        val order = OrderRedis(
            optionIdList = orderDto.optionIdList,
            productIdList = orderDto.productIdList,
            accountId = orderDto.accountId,
            totalMoney = orderDto.totalMoney
        )
        order.merchantUid = createMerchantUid(order)
        orderRedisRepository.save(order)
        //ToDo: Mapstruct 작업
        return OrderResponseDto(
            createdAt = order.createdAt,
            merchantUid = order.merchantUid,
            optionIdList = order.optionIdList,
            productIdList = order.productIdList
        )
    }

    private fun createMerchantUid(orderRedis: OrderRedis): String {
        return "ORD-${orderRedis.createdAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"))}-${UUID.randomUUID()}"
    }

    @Transactional(readOnly = true)
    fun getOrder(merchantUid: String): OrderResponseDto {
        val orderRedis: OrderRedis = orderRedisRepository.findByMerchantUid(merchantUid) ?: throw OrderPayException(ErrorCode.ORDER_NOT_FOUND)
        //ToDo: Mapstruct 작업
        return OrderResponseDto(
            createdAt = orderRedis.createdAt,
            merchantUid = orderRedis.merchantUid,
            optionIdList = orderRedis.optionIdList,
            productIdList = orderRedis.productIdList
        )
    }
}