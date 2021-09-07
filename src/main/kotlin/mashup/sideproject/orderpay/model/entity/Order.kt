package mashup.sideproject.orderpay.model.entity

import mashup.sideproject.orderpay.model.enums.PayStatus
import mashup.sideproject.orderpay.support.OrderConverter
import javax.persistence.*


@Entity
@Table(name = "orderrecord")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val accountId: Long,
    val impUid: String,
    val merchantUid: String,
    var payStatus: PayStatus,
    val payMethod: String,
    val paidAmount: Int,
    val name: String,

    var paidAt: Int? = null,

    @Embedded
    var buyerInfo: BuyerInfo? = null,

    @Convert(converter = OrderConverter::class)
    var productIdList: List<Long>,

    @Convert(converter = OrderConverter::class)
    var optionIdList: List<Long>
) : BaseEntity() {
    companion object {
        fun of(orderRedis: OrderRedis): Order {
            return Order(productIdList = orderRedis.productIdList, optionIdList = orderRedis.optionIdList)
        }
    }
}