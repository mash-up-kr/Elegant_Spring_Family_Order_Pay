package mashup.sideproject.orderpay.infrastructure

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface OpLogger {
    val log: Logger get() = LoggerFactory.getLogger(this.javaClass)
}