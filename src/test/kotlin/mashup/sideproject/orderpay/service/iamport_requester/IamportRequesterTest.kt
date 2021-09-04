package mashup.sideproject.orderpay.service.iamport_requester

import mashup.sideproject.orderpay.infrastructure.OpLogger
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(value = ["local","test"])
internal class IamportRequesterTest {
    @Autowired
    lateinit var iamportRequester: IamportRequester

    companion object : OpLogger

    @Test
    @DisplayName("결제내역 확인")
    fun requestPayments() {
        val paymentsResponse = iamportRequester.requestPayments("imp_415921100266")
        assertNotNull(paymentsResponse.response)
        log.debug("response -> ${paymentsResponse.response}")
    }
}