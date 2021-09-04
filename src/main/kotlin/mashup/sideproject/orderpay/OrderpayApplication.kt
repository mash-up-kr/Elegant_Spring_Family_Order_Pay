package mashup.sideproject.orderpay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class OrderpayApplication

fun main(args: Array<String>) {
    runApplication<OrderpayApplication>(*args)
}
