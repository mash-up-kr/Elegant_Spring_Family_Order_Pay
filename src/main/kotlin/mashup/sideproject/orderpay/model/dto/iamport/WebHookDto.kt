package mashup.sideproject.orderpay.model.dto.iamport

import mashup.sideproject.orderpay.model.dto.PaymentInfo
import mashup.sideproject.orderpay.model.enums.PayStatus

data class WebHookDto(
    override val impUid: String,
    override val merchantUid: String,
    val payStatus: PayStatus
) : PaymentInfo