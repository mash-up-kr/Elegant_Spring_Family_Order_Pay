package mashup.sideproject.orderpay.model.dto.iamport.payments

import mashup.sideproject.orderpay.model.dto.iamport.BaseDto
import java.math.BigDecimal

data class PaymentCancelRequestDto(
    val impUid: String,
    val merchantUid: String,
    val amount: BigDecimal? = null, // (부분)취소 요청 금액(누락이면 전액 취소)
    val taxFree: BigDecimal? = null, // (부분)취소 요청 금액 중 면세금액(누락되면 0원처리)
    val checkSum: BigDecimal? = null, // API 요청자의 데이터와 iamport의 데이터 일치 검증
    val reason: String,
    val refundHolder: String? = null, // 환불계좌 예금주
    val refundBank: String? = null, // 환분계좌 은행코드
    val refundAccount: String? = null, // 환분계좌 계좌 번호
    val refundTel: String? = null // 환불계좌 예금주 연락처
) : BaseDto