package mashup.sideproject.orderpay.service

import mashup.sideproject.orderpay.service.iamport_requester.IamportRequester
import org.springframework.stereotype.Service

@Service
class PayVerificationService(private val iamportRequester: IamportRequester) {

}