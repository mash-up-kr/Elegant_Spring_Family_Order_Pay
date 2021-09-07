package mashup.sideproject.orderpay.service.consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mashup.sideproject.orderpay.model.dto.event.PaymentDoneEvent
import mashup.sideproject.orderpay.service.PaymentDoneService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException
import java.util.function.Consumer

@Configuration
class PaymentDoneEventConsumer(private val paymentDoneService: PaymentDoneService) {
    private val mapper = jacksonObjectMapper()

    @Bean
    fun paymentDone(): Consumer<String> {
        return Consumer { o: String? ->
            run {
                try {
                    val event = mapper.readValue(o, PaymentDoneEvent::class.java)
                    recordPayment(event)
                } catch (e: IOException) {

                }
            }
        }
    }

    fun recordPayment(event: PaymentDoneEvent) {
        paymentDoneService.saveOrder(event)
    }
}