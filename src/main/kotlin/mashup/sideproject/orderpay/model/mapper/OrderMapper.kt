package mashup.sideproject.orderpay.model.mapper

import mashup.sideproject.orderpay.model.dto.order.OrderResponseDto
import mashup.sideproject.orderpay.model.dto.order.OrderSubmitRequestDto
import mashup.sideproject.orderpay.model.entity.Order
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface OrderMapper {
    @Mappings(
        Mapping(source = "orderSubmitRequestDto.merchantUid", target = "merchantUid"),
        Mapping(source = "orderSubmitRequestDto.buyerName", target = "buyerInfo.buyerName"),
        Mapping(source = "orderSubmitRequestDto.buyerEmail", target = "buyerInfo.buyerEmail"),
        Mapping(source = "orderSubmitRequestDto.buyerTel", target = "buyerInfo.buyerTel"),
        Mapping(source = "orderSubmitRequestDto.buyerAddr", target = "buyerInfo.buyerAddr"),
        Mapping(source = "orderSubmitRequestDto.buyerPostcode", target = "buyerInfo.buyerPostcode"),
        Mapping(source = "orderResponseDto.productIdList", target = "productIdList"),
        Mapping(source = "orderResponseDto.optionIdList", target = "optionIdList")
    )
    fun toOrder(orderSubmitRequestDto: OrderSubmitRequestDto, orderResponseDto: OrderResponseDto): Order
}