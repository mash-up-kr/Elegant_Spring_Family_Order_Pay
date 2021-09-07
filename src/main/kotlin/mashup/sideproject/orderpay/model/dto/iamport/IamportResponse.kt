package mashup.sideproject.orderpay.model.dto.iamport

import mashup.sideproject.orderpay.model.dto.BaseDto

class IamportResponse<T>(
    val code: Int?,
    val message: String?,
    val response: T?,
) : BaseDto
