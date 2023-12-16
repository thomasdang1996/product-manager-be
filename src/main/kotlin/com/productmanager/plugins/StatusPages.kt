package com.productmanager.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages(){
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(
                "500: ${cause.stackTraceToString()}",
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}