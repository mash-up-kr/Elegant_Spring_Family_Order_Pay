package mashup.sideproject.orderpay.model.dto.iamport

import mashup.sideproject.orderpay.model.dto.BaseDto

data class TokenResponseDto(
    val accessToken: String,
    val expiredAt: Int,
    val now: Int
) : BaseDto
