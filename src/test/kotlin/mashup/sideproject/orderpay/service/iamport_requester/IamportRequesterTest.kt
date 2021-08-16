package mashup.sideproject.orderpay.service.iamport_requester

import mashup.sideproject.orderpay.infrastructure.OpLogger
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(value = ["local", "test"])
internal class IamportRequesterTest {
    @Autowired
    lateinit var iamportRequester: IamportRequester

    companion object : OpLogger

    @Test
    fun requestBalance() {
        iamportRequester.requestBalance("testImp")
    }
}