package mashup.sideproject.orderpay.model.dto.iamport

data class TokenResponseDto(
    val accessToken: String,
    val expiredAt: Int,
    val now: Int
) : BaseDto
