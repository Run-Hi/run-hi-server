package org.runhi.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.LOCATION
import org.springframework.http.HttpHeaders.SET_COOKIE
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins("http://localhost:3000/")
            .allowCredentials(true)
            .exposedHeaders(LOCATION, SET_COOKIE)
    }
}
