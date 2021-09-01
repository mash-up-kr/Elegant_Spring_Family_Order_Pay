package mashup.sideproject.orderpay.model.entity

import mashup.sideproject.orderpay.support.OrderConverter
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

    @Convert(converter = OrderConverter::class)
    var productIdList: List<Long>,

    @Convert(converter = OrderConverter::class)
    var optionIdList: List<Long>
) : BaseEntity()