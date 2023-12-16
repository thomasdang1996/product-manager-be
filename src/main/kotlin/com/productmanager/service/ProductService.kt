package com.productmanager.service

import com.productmanager.dto.ProductType
import com.productmanager.dto.NewProductRequest
import com.productmanager.dto.ProductDto
import com.productmanager.dto.ProductsByTypeDto
import com.productmanager.mapper.toProductDto
import com.productmanager.mapper.toProductsByType
import com.productmanager.repository.ProductRepository
import java.util.*

class ProductService(private val repository: ProductRepository) {
    fun findById(id: UUID): ProductDto {
        return repository.findById(id).toProductDto()
    }

    fun addNewProduct(newProductRequest: NewProductRequest) {
        repository
            .insertProduct(newProductRequest)
    }

    fun getProductNames(productTypeCode: String): ProductsByTypeDto {
        val type: ProductType = ProductType.geTypeByCode(productTypeCode)
        return repository.findByProductType(type).toProductsByType()
    }

    fun getAllProducts(): List<ProductDto> {
        return repository
            .getAllProducts()
            .map { ProductDto(it.id, it.name, it.productType.code, it.price.toLong()) }
            .toList()
    }
}
