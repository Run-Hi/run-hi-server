package org.runhi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RunhiApplication

fun main(args: Array<String>) {
    runApplication<RunhiApplication>(*args)
}
