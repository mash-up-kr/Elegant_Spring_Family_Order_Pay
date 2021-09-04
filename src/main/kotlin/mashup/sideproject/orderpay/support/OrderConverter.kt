package mashup.sideproject.orderpay.support

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class OrderConverter : AttributeConverter<List<Long>, String> {
    override fun convertToEntityAttribute(dbData: String): List<Long> {
        return dbData.split(",").map { it.toLong() } ?: emptyList()
    }

    override fun convertToDatabaseColumn(attribute: List<Long>): String {
        return attribute.joinToString(",") ?: ""
    }
}