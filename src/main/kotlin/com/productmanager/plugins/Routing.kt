package com.productmanager.plugins

import com.productmanager.controller.pearStoreRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    // Negotiating media types between the client and server. For this, it uses the Accept and Content-Type headers.
    configureClientNegotiation()
    configureServerNegotiation()
    configureStatusPages()

    routing {
        route("api") {        // Verb, Pattern, Handler idiom
            // Verb - get
            // Pattern - /user
            // Handler - body of code
            pearStoreRoute()
        }
    }
}