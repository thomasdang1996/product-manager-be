package com.productmanager

import com.productmanager.plugins.configureKoin
import com.productmanager.plugins.configureMonitoring
import com.productmanager.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(IgnoreTrailingSlash)        //ignore the last slash if typed
    configureKoin()         //Always before configureRouting!
    configureMonitoring()
    configureRouting()
}
