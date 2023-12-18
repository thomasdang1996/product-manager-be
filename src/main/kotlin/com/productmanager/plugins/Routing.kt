package com.productmanager.plugins

import com.productmanager.controller.pearStoreRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

/**
 * Configures routing and creates a controller layer for the application
 */
fun Application.configureRouting() {
    configureContentNegotiation()
    configureStatusPages()

    routing {
        route("api") {
            pearStoreRoute()
        }
    }
}