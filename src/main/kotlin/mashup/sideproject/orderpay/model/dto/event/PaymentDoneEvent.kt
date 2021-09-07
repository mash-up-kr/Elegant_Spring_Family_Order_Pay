package mashup.sideproject.orderpay.model.dto.event

data class PaymentDoneEvent(
        val accountId: String,
        val merchantUid: String,
        val impUid: String
)