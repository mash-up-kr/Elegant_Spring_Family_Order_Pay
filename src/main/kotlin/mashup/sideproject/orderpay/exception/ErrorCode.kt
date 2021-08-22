package mashup.sideproject.orderpay.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(val httpStatus: Int, val description: String) {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "인증 Token이 전달되지 않았거나 유효하지 않습니다."),
    INVALID_IMP_UID(HttpStatus.NOT_FOUND.value(), "유효하지 않은 imp_uid 입니다."),
    ORDER_NOT_FOUND(404, "Order Not Found"),

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Entity Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST.value(), " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "Access is Denied"),
    ETC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버 오류입니다.")
}