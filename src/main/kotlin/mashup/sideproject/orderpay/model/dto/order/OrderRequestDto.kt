package mashup.sideproject.orderpay.model.dto.order

import mashup.sideproject.orderpay.model.dto.iamport.BaseDto

data class OrderRequestDto(
    val productIdList: List<Long> = ArrayList(),
    val optionIdList: List<Long> = ArrayList()
) : BaseDto