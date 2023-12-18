package com.productmanager.mapper

import com.productmanager.ProductEntity
import com.productmanager.dto.NewProductRequest
import com.productmanager.dto.ProductDto
import com.productmanager.dto.ProductType
import com.productmanager.dto.ProductsByTypeDto
import java.util.*

fun ProductEntity.toProductDto(): ProductDto {
    return ProductDto(
        this.id,
        this.name,
        this.productType.code,
        this.price
    )
}
fun NewProductRequest.toProductEntity(): ProductEntity{
    return ProductEntity(
        UUID.randomUUID().toString(),
        this.name,
        ProductType.geTypeByCode(this.productTypeCode),
        this.price.toBigDecimal()
    )
}

fun List<ProductEntity>.toProductsByType(): ProductsByTypeDto {
    return ProductsByTypeDto(
        this.stream()
            .map { ProductsByTypeDto.Product(UUID.fromString(it.id), it.name) }
            .toList()
    )
}