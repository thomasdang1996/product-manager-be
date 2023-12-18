package com.productmanager.dto

import com.productmanager.utils.BigDecimalJson
import kotlinx.serialization.Serializable

@Serializable
class ProductDto(
    val id: String,
    val name: String,
    val productTypeCode: String,
    val price: BigDecimalJson
)