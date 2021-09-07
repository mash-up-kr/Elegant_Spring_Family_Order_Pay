package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.model.dto.event.PaymentDoneEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentDoneService {
    fun requestItemInformation() {}

    @Transactional
    fun saveOrder(paymentDoneEvent: PaymentDoneEvent) {

    }
}