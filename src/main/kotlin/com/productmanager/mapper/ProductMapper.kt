package com.productmanager.mapper

import com.productmanager.ProductEntity
import com.productmanager.dto.ProductDto
import com.productmanager.dto.ProductsByTypeDto
import java.util.*

fun ProductEntity.toProductDto(): ProductDto {
    return ProductDto(
        this.id,
        this.name,
        this.productType.code,
        this.price.toLong()
    )
}

fun List<ProductEntity>.toProductsByType(): ProductsByTypeDto {
    return ProductsByTypeDto(
        this.stream()
            .map { ProductsByTypeDto.Product(UUID.fromString(it.id), it.name) }
            .toList()
    )
}