package org.runhi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@SpringBootApplication
class RunhiApplication

fun main(args: Array<String>) {
    runApplication<RunhiApplication>(*args)
}
