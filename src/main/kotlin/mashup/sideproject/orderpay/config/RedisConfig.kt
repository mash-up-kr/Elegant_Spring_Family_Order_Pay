package mashup.sideproject.orderpay.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
class RedisConfig(@Value("\${spring.redis.host}")
                  private val redisHost: String? = null,
                  @Value("\${spring.redis.port}")
                  private val redisPort: Int = 6379,
                  @Value("\${spring.redis.password}")
                  private val redisPassword: String? = null
) {
    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {

        val redisStandaloneConfiguration = RedisStandaloneConfiguration(redisHost!!, redisPort)
        redisStandaloneConfiguration.setPassword(redisPassword)

        return JedisConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean
    fun redisTemplateWithJedis(

    ): RedisTemplate<String, Any> {

        val template = RedisTemplate<String, Any>()
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()

        template.setConnectionFactory(jedisConnectionFactory())
        template.setEnableTransactionSupport(true)

        return template
    }
}