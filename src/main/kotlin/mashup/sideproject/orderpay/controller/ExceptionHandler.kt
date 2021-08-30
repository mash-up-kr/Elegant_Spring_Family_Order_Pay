package mashup.sideproject.orderpay.controller

import mashup.sideproject.orderpay.exception.ErrorCode
import mashup.sideproject.orderpay.exception.ErrorResponseDto
import mashup.sideproject.orderpay.exception.OrderPayException
import mashup.sideproject.orderpay.infrastructure.OpLogger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {

    companion object : OpLogger

    @ExceptionHandler(OrderPayException::class)
    fun orderPayExceptionHandler(request: HttpServletRequest, e: OrderPayException): ResponseEntity<ErrorResponseDto> {
        log.error("@ERROR@ - occur OrderPay Error -> ", e)
        return ResponseEntity.status(e.errorCode.httpStatus).body(ErrorResponseDto(e.errorCode))
    }

    @ExceptionHandler(Exception::class)
    fun etcExceptionHandler(request: HttpServletRequest, e: Exception): ResponseEntity<ErrorResponseDto> {
        log.error("@ERROR - occur ETC Error -> ", e)
        return ResponseEntity.status(ErrorCode.ETC_ERROR.httpStatus).body(ErrorResponseDto(ErrorCode.ETC_ERROR))
    }

}