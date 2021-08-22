package mashup.sideproject.orderpay.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable
import java.time.LocalDateTime

@RedisHash("order")
data class Order(
    @Id
    val id: String? = null,
    var productIdList: List<Long> = ArrayList(),
    var optionIdList: List<Long> = ArrayList(),
    @Indexed
    var merchantUid: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
) : Serializable