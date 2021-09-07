package mashup.sideproject.orderpay.service.producer

import mashup.sideproject.orderpay.model.dto.event.PaymentDoneEvent
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Component

@Component
class PaymentDoneEventProducer(private val streamBridge: StreamBridge) {
    fun publishPaymentDoneEvent(paymentDoneEvent: PaymentDoneEvent) {
        streamBridge.send("payment", paymentDoneEvent)
    }
}