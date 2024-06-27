package org.runhi.common.config

import org.runhi.auth.interceptor.AuthInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.LOCATION
import org.springframework.http.HttpHeaders.SET_COOKIE
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig(
    private val authInterceptor: AuthInterceptor,
) : WebMvcConfigurer {
    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins(
                "https://runhi.org/",
                "http://localhost:8888/",
                "http://localhost:3000",
            )
            .allowCredentials(true)
            .exposedHeaders(LOCATION, SET_COOKIE)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }
}
