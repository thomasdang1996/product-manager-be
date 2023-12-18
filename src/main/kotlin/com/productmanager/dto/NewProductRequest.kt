package com.productmanager.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class NewProductRequest(
    val name: String,
    val productTypeCode: String,
    @Contextual val price: Double
) {
    override fun toString(): String {
        return "NewProductRequest(name='$name', productTypeCode='$productTypeCode', price=$price)"
    }
}