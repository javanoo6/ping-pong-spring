package com.example.javanoo6.webpart

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@AutoConfiguration
@SpringBootApplication(scanBasePackages = ["com.example.javanoo6"])
class SpringPingPongApplication

fun main(args: Array<String>) {
    runApplication<SpringPingPongApplication>(*args)
}
