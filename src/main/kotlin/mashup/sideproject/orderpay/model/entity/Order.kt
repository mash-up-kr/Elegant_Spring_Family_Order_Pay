package mashup.sideproject.orderpay.model.entity

import javax.persistence.*

@Entity
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val impUid: String? = null,

    val merchantUid: String? = null,

    val payMethod: String? = null,

    val paidAmount: Int? = null,

    val name: String? = null,

    val paidAt: Long? = null,

    @Embedded
    var buyerInfo: BuyerInfo? = null,

    //jpa converter
    var productIdList: String? = null,

    //jpa converter
    var optionIdList: String? = null
) : BaseEntity()