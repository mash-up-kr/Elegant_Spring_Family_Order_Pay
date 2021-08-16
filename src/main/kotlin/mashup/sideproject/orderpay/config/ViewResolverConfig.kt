package mashup.sideproject.orderpay.config

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode

@Configuration
class ViewResolverConfig(private val context: ApplicationContext) : WebMvcConfigurer {

    @Bean
    fun thymeleafTemplateResolver() = SpringResourceTemplateResolver().apply {
        this.setApplicationContext(context)
        this.prefix = "classpath:templates/"
        this.suffix = ".html"
        this.templateMode = TemplateMode.HTML
        this.isCacheable = false
        this.checkExistence = false
    }

    @Bean
    fun thymeleafTemplateEngine() = SpringTemplateEngine().apply {
        this.setTemplateResolver(thymeleafTemplateResolver())
    }

    @Bean
    fun thymeleafViewResolver() = ThymeleafViewResolver().apply {
        this.templateEngine = thymeleafTemplateEngine()
        this.characterEncoding = Charsets.UTF_8.name()
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.viewResolver(thymeleafViewResolver())
    }
}