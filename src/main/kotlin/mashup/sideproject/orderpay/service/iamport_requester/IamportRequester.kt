package mashup.sideproject.orderpay.service.iamport_requester

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.extension.uniCodeToPlainString
import mashup.sideproject.orderpay.infrastructure.OpLogger
import mashup.sideproject.orderpay.model.dto.iamport.IamportResponse
import mashup.sideproject.orderpay.model.dto.iamport.payments.PaymentAnnotation
import mashup.sideproject.orderpay.model.dto.iamport.payments.PaymentCancelRequestDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

@Component
class IamportRequester(private val iamportWebClient: WebClient) {

    companion object : OpLogger {
        const val BALANCE_API_URL = "/payments/{imp_uid}"
        const val CANCEL_API_URL = "/payments/cancel"
    }

    fun requestPayment(impUid: String): IamportResponse<PaymentAnnotation> {
        return iamportWebClient.get()
            .uri(BALANCE_API_URL, impUid)
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<IamportResponse<PaymentAnnotation>>() {})
            .onErrorResume(WebClientResponseException::class.java) { e ->
                when (e.statusCode) {
                    HttpStatus.UNAUTHORIZED -> Mono.error(OrderPayException(ErrorCode.INVALID_TOKEN, "response:${e.responseBodyAsString.uniCodeToPlainString()}", e))
                    HttpStatus.NOT_FOUND -> Mono.error(OrderPayException(ErrorCode.INVALID_IMP_UID, "response:${e.responseBodyAsString.uniCodeToPlainString()}"))
                    HttpStatus.METHOD_NOT_ALLOWED -> Mono.error(OrderPayException(ErrorCode.METHOD_NOT_ALLOWED, "response:${e.responseBodyAsString.uniCodeToPlainString()}"))
                    else -> Mono.error(e)
                }
            }
            .block()!!
    }

    fun requestPaymentCancel(paymentCancelRequestDto: PaymentCancelRequestDto) {
        iamportWebClient.post()
            .uri(CANCEL_API_URL)
            .body(BodyInserters.fromValue(paymentCancelRequestDto))
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<IamportResponse<PaymentAnnotation>>() {})
            .onErrorResume(WebClientResponseException::class.java) { e ->
                when (e.statusCode) {
                    HttpStatus.UNAUTHORIZED -> Mono.error(OrderPayException(ErrorCode.INVALID_TOKEN, "response:${e.responseBodyAsString.uniCodeToPlainString()}", e))
                    else -> Mono.error(e)
                }
            }
            .block()!!
    }
}