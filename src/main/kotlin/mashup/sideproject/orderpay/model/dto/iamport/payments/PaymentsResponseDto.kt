package mashup.sideproject.orderpay.model.dto.iamport.payments

import mashup.sideproject.orderpay.model.dto.iamport.BaseDto

data class PaymentsResponseDto(
    val impUid: String,
    val merchantUid: String,
    val payMethod: String?,
    val channel: String?,
    val pgProvider: String?,
    val embPgProvider: String?,
    val pgTid: String?,
    val pgId: String?,
    val escrow: Boolean?,
    val applyNum: String?,
    val bankCode: String?,
    val bankName: String?,
    val cardCode: String?,
    val cardName: String?,
    val cardQuota: Int?,
    val cardNumber: String?,
    val cardType: Int?,
    val vbankCode: String?,
    val vbankName: String?,
    val vbankNum: String?,
    val vbankHolder: String?,
    val vbankDate: Int?,
    val vbankIssued_at: Int?,
    val name: String?,
    val amount: Int,
    val cancelAmount: Int,
    val currency: String,
    val buyerName: String?,
    val buyerEmail: String?,
    val buyerTel: String?,
    val buyerAddr: String?,
    val buyerPostcode: String?,
    val customData: String?,
    val userAgent: String?,
    val status: String,
    val startedAt: Int?,
    val paidAt: Int?,
    val failedAt: Int?,
    val cancelledAt: Int?,
    val failReason: String?,
    val cancelReason: String?,
    val receiptUrl: String?,
    val cancelHistory: List<PaymentCancelAnnotation>?,
    val cancelReceiptUrls: List<String>?,
    val cashReceiptIssued: Boolean?,
    val customerUid: String?,
    val customerUidUsage: String?
) : BaseDto {
    data class PaymentCancelAnnotation(
        val pgTid: String,
        val amount: Int,
        val cancelledAt: Int,
        val reason: String,
        val receiptUrl: String?
    ) : BaseDto
}