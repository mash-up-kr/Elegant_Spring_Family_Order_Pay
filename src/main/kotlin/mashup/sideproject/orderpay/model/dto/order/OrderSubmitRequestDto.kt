package mashup.sideproject.orderpay.model.dto.order

import mashup.sideproject.orderpay.model.dto.BaseDto

data class OrderSubmitRequestDto (
    val impUid: String? = null,

    val merchantUid: String,

    val payMethod: String? = null,

    val paidAmount: Int? = null,

    val name: String? = null,

    val paidAt: Long? = null,

    var buyerName: String? = null,

    var buyerEmail: String? = null,

    var buyerTel: String? = null,

    var buyerAddr: String? = null,

    var buyerPostcode: String? = null
) : BaseDto