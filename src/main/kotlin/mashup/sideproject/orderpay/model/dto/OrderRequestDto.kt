package mashup.sideproject.orderpay.model.dto

import java.util.*

data class OrderRequestDto(
        val productIdList: List<Long>? = ArrayList(),
        val optionIdList: List<Long>? = ArrayList()
)