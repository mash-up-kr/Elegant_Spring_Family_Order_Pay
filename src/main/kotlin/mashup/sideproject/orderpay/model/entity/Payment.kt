package mashup.sideproject.orderpay.model.entity

import mashup.sideproject.orderpay.support.OrderConverter
import javax.persistence.*

@Entity
data class Payment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val impUid: String? = null,

        val merchantUid: String? = null,
) : BaseEntity()