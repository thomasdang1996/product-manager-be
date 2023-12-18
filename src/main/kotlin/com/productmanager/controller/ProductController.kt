package com.productmanager.controller

import com.productmanager.dto.NewProductRequest
import com.productmanager.service.ProductService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.error.MissingPropertyException
import org.koin.ktor.ext.inject
import java.util.*

/**
 * Kotlin file representing the controller layer of the application
 */
fun Route.pearStoreRoute() {
    val productService: ProductService by inject<ProductService>()
    route("pear-store-be") {
        post("/product") {
            val newProductRequest = call.receive<NewProductRequest>()
            call.respond(productService.addNewProduct(newProductRequest))
        }
        get("/product/{id}") {
            val productId = call.parameters["id"] ?: throw MissingPropertyException("missing id")
            if (productId.isUUID()) {
                call.respond(productService.findById(productId))
            } else {
                call.respondText { "Id is not in UUID format: $productId" }
            }
        }
        get("/products") {      // indexing operator
            call.respond(productService.getAllProducts())
        }
        get("/getProductNames") {
            val code = call.request.queryParameters["productTypeCode"]
                ?: throw Exception("missing productTypeCode")
            call.respond(productService.getProductNames(code))
        }
    }
}

private fun String.isUUID(): Boolean {
    return try {
        UUID.fromString(this)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}