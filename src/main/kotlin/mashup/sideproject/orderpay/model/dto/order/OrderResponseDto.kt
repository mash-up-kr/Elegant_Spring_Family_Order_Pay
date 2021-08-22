package mashup.sideproject.orderpay.model.dto.order

import mashup.sideproject.orderpay.model.dto.iamport.BaseDto
import java.time.LocalDateTime

data class OrderResponseDto(
    val merchantUid: String? = null,
    val productIdList: List<Long> = ArrayList(),
    val optionIdList: List<Long> = ArrayList(),
    val createdAt: LocalDateTime = LocalDateTime.now()
) : BaseDto