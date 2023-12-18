package com.productmanager.plugins

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientNegotiation
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ServerNegotiation

/**
 * Negotiating media types between the client and server.
 * For this, it uses the Accept and Content-Type headers.
 */
fun Application.configureContentNegotiation() {
    install(ServerNegotiation) {
        json()
    }
    HttpClient(CIO) {
        install(ClientNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
}
