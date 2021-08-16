package mashup.sideproject.orderpay.model.dto.iamport

class IamportResponse<T>(
    val code: Int?,
    val message: String?,
    val response: T?,
) : BaseDto
