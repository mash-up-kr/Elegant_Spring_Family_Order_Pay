package mashup.sideproject.orderpay.model.entity

import javax.persistence.Embeddable
import javax.persistence.Lob

@Embeddable
data class BuyerInfo(
    var buyerName: String? = null,

    var buyerEmail: String? = null,

    var buyerTel: String? = null,

    @Lob
    var buyerAddr: String? = null,

    var buyerPostcode: String? = null
)