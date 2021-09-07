package mashup.sideproject.orderpay.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import mashup.sideproject.orderpay.model.dto.BaseDto

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorResponseDto(
    val errorCode: String,
    val errorMessage: String
) : BaseDto {
    constructor(errorCode: ErrorCode) : this(errorCode.name, errorCode.description)
}