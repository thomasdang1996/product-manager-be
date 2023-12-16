package com.productmanager.dto

import kotlinx.serialization.Serializable

@Serializable
class ProductDto(
   val id: String,
    val name: String,
    val productTypeCode: String,
    val price: Long
)