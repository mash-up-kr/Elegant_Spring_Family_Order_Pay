package mashup.sideproject.orderpay.model.repository

import mashup.sideproject.orderpay.model.entity.OrderRedis
import org.springframework.data.repository.CrudRepository

interface OrderRedisRepository : CrudRepository<OrderRedis, Long> {
    fun findByMerchantUid(merchantUid: String): OrderRedis?
    fun deleteByMerchantUid(merchantUid: String)
}