package mashup.sideproject.orderpay.service.iamport_requester

import mashup.sideproject.orderpay.model.dto.iamport.IamportResponse
import mashup.sideproject.orderpay.model.dto.iamport.payments.BalanceResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

@Component
class IamportRequester(
    @Value("\${iamport.api-key}") private val iamportApiKey: String,
    @Value("\${iamport.api-secret}") private val iamportSecretKey: String,
    private val iamportWebClient: WebClient
) {

    companion object {
        const val BALANCE_API_URL = "/payments/{imp_uid}/balance"
    }

    fun requestBalance(impUid: String): IamportResponse<BalanceResponseDto> {
        return iamportWebClient.get()
            .uri(BALANCE_API_URL, impUid)
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<IamportResponse<BalanceResponseDto>>() {})
            .onErrorResume(WebClientResponseException::class.java) { e ->
                when (e.statusCode) {
                    HttpStatus.UNAUTHORIZED -> throw RuntimeException()
                    HttpStatus.BAD_REQUEST -> throw RuntimeException()
                    HttpStatus.METHOD_NOT_ALLOWED -> throw RuntimeException()
                    else -> throw RuntimeException()
                }
            }
            .block()!!
    }
}