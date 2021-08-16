package mashup.sideproject.orderpay.config

import mashup.sideproject.orderpay.model.dto.iamport.IamportResponse
import mashup.sideproject.orderpay.model.dto.iamport.TokenResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Configuration
class WebClientConfig(
    @Value("\${iamport.host}") private val iamportHost: String,
    @Value("\${iamport.api-key}") private val iamportApiKey: String,
    @Value("\${iamport.api-secret}") private val iamportSecretKey: String
) {

    companion object {
        const val HEADER_ATTRIBUTE_IMP_KEY = "imp_key"
        const val HEADER_ATTRIBUTE_IMP_SECRET = "imp_secret"
        const val HEADER_ATTRIBUTE_AUTHORIZATION = "Authorization"
    }

    @Bean
    fun tokenWebClient(): WebClient = createWebClient("$iamportHost/users/getToken").build()

    @Bean
    fun iamportWebClient(): WebClient {
        return createWebClient(iamportHost)
            .filter(
                ExchangeFilterFunction.ofRequestProcessor { originalClientRequest ->
                    ClientRequest.from(originalClientRequest)
                        .header(HEADER_ATTRIBUTE_AUTHORIZATION, getIamportToken().response?.accessToken).build().let {
                            Mono.just(it)
                        }
                }
            ).build()
    }

    private fun createWebClient(host: String): WebClient.Builder {
        return WebClient.builder()
            .baseUrl(host)
            .defaultHeaders { httpHeaders ->
                httpHeaders.contentType = MediaType.APPLICATION_FORM_URLENCODED
                httpHeaders.accept = listOf(MediaType.APPLICATION_JSON)
            }
    }

    private fun getIamportToken(): IamportResponse<TokenResponseDto> {
        return tokenWebClient().post()
            .body(BodyInserters.fromFormData(LinkedMultiValueMap<String, String>().apply {
                this.add(HEADER_ATTRIBUTE_IMP_KEY, iamportApiKey)
                this.add(HEADER_ATTRIBUTE_IMP_SECRET, iamportSecretKey)
            }))
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<IamportResponse<TokenResponseDto>>() {})
            .block()!!
    }
}