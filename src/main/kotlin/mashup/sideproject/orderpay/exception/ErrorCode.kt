package mashup.sideproject.orderpay.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
        val code: Int,
        val message: String
) {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Entity Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST.value(), " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "Access is Denied"),

    // Order
    ORDER_NOT_FOUND(404, "Order Not Found");
}