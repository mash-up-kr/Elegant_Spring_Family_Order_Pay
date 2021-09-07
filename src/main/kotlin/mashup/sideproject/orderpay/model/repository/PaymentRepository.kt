package mashup.sideproject.orderpay.model.repository

import mashup.sideproject.orderpay.model.entity.Payment
import org.springframework.data.repository.CrudRepository

interface PaymentRepository : CrudRepository<Payment, Long> {
}