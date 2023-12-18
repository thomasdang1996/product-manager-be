package com.productmanager.dto

import java.util.*

class ProductsByTypeDto(val products: List<Product>) {
    class Product(
        val id: UUID,
        val name: String
    )
}