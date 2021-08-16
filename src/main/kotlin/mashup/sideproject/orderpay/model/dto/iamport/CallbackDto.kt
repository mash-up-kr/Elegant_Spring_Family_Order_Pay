package mashup.sideproject.orderpay.model.dto.iamport

import mashup.sideproject.orderpay.model.enums.PayStatus

data class CallbackDto(
    val impUid: String,
    val merchantUid: String,
    val payStatus: PayStatus
)