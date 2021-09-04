package mashup.sideproject.orderpay.model.repository

import mashup.sideproject.orderpay.model.entity.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long> {
}