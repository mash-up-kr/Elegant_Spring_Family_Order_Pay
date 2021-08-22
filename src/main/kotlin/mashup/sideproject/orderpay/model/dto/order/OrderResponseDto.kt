package mashup.sideproject.orderpay.model.dto.order

import mashup.sideproject.orderpay.model.dto.iamport.BaseDto

data class OrderResponseDto(
    val merchantUid: String
) : BaseDto
