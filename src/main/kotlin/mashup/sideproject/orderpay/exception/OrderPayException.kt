package mashup.sideproject.orderpay.exception

class OrderPayException(val errorCode: ErrorCode, val throwable: Throwable?) : RuntimeException(errorCode.description, throwable) {
}