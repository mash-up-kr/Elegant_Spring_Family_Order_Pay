package mashup.sideproject.orderpay.model.dto.order

import mashup.sideproject.orderpay.model.dto.BaseDto
import java.math.BigDecimal

data class OrderRequestDto(
    val productIdList: List<Long> = ArrayList(),
    val optionIdList: List<Long> = ArrayList(),
    val accountId: Long,
    val amount: BigDecimal
) : BaseDto