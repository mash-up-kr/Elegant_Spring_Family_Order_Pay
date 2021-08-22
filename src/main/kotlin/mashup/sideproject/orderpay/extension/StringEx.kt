package mashup.sideproject.orderpay.extension

fun String.uniCodeToPlainString(): String {
    return StringBuffer().let { stringBuffer ->
        var i = 0
        while (i < this.length) {
            if ('\\' == this[i] && 'u' == this[i + 1]) {
                this.substring(i + 2, i + 6).toInt(16).toChar().let {
                    stringBuffer.append(it)
                    i += 5
                }
            } else {
                stringBuffer.append(this[i])
            }
            i++
        }
        stringBuffer.toString()
    }
}