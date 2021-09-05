package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.infrastructure.OpLogger
import org.springframework.stereotype.Service

@Service
class PaymentService(private val verifyTransactionSupporter: VerifyTransactionSupporter) {

    companion object : OpLogger

    fun payment() {
        runCatching {

        }.onSuccess {

        }.onFailure { e ->
            if (e is OrderPayException) {
                when (e.errorCode) {
                    ErrorCode.ALREADY_PAID_ORDER -> {
                    }
                    ErrorCode.ORDER_NOT_FOUND -> {
                    }
                    ErrorCode.FORGED_PAYMENT -> {
                    }
                }
            } else {
                log.error("")
            }
        }
    }
}