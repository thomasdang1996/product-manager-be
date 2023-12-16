package com.productmanager.controller

import com.productmanager.dto.NewProductRequest
import com.productmanager.service.ProductService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.pearStoreRoute() {
    val productService: ProductService by inject<ProductService>()
    route("pear-store-be") {
        post("/product") {
            val newProductRequest = call.receive<NewProductRequest>()
            call.respond(productService.addNewProduct(newProductRequest))
        }

        get("/product/{id}") {
            val productId = call.parameters["id"]
                ?: throw Exception("missing id")         // indexing operator
            call.respond(productService.findById(UUID.fromString(productId)))
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