package mashup.sideproject.orderpay.model.dto.iamport.payments

import com.fasterxml.jackson.annotation.JsonUnwrapped
import mashup.sideproject.orderpay.model.dto.iamport.BaseDto

data class BalanceResponseDto(
    val amount: Int,
    @JsonUnwrapped
    val balances: Balances,
    val histories: List<Balances>
) : BaseDto {
    data class Balances(
        val cashReceipt: BalanceDetail,
        val primary: BalanceDetail,
        val secondary: BalanceDetail,
        val discount: BalanceDetail
    )

    data class BalanceDetail(
        val taxFree: Int,
        val supply: Int,
        val vat: Int,
        val service: Int
    )

}