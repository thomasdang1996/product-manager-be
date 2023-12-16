package com.productmanager

import com.productmanager.plugins.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val port = environment.config.propertyOrNull("ktor.deployment.port")?.getString() ?: "9999"
    println("Running on port $port")
    install(IgnoreTrailingSlash)        //ignore the last slash if typed
    configureKoin()         //Always before configureRouting!
    configureMonitoring()
    configureRouting()
}
