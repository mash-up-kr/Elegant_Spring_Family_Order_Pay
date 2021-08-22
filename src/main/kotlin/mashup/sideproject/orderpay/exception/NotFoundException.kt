package mashup.sideproject.orderpay.exception

import org.springframework.http.HttpStatus

class NotFoundException(
        val errorCode: ErrorCode
) : BaseException(errorCode.code, errorCode.message, HttpStatus.NOT_FOUND)