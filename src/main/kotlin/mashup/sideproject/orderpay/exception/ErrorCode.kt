package mashup.sideproject.orderpay.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val httpStatus: Int, val description: String) {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "인증 Token이 전달되지 않았거나 유효하지 않습니다."),
    INVALID_IMP_UID(HttpStatus.NOT_FOUND.value(), "유효하지 않은 imp_uid 입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "허용되지 않는 HTTP METHOD 입니다."),
    ETC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버 오류입니다.")
}