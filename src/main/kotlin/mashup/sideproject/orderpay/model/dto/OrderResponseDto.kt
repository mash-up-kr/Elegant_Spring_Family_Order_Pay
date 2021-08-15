package mashup.sideproject.orderpay.model.dto

import java.time.LocalDateTime
import java.util.*

data class OrderResponseDto(
        val merchantUid: String? = null,
        val productIdList: List<Long>? = ArrayList(),
        val optionIdList: List<Long>? = ArrayList(),
        val createdAt: LocalDateTime? = LocalDateTime.now()
)