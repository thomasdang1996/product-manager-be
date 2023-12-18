package com.productmanager.service

import com.productmanager.dto.NewProductRequest
import com.productmanager.dto.ProductDto
import com.productmanager.dto.ProductType
import com.productmanager.dto.ProductsByTypeDto
import com.productmanager.mapper.toProductDto
import com.productmanager.mapper.toProductsByType
import com.productmanager.repository.ProductRepository

class ProductService(private val repository: ProductRepository) {
    fun findById(id: String): ProductDto {
        return repository
            .findById(id)
            .toProductDto()
    }

    fun addNewProduct(newProductRequest: NewProductRequest) {
        println("NewProductRequest: $newProductRequest")
        repository.insertProduct(newProductRequest)
    }

    fun getProductNames(productTypeCode: String): ProductsByTypeDto {
        val type: ProductType = ProductType.geTypeByCode(productTypeCode)
        return repository
            .findByProductType(type)
            .toProductsByType()
    }

    fun getAllProducts(): List<ProductDto> {
        return repository
            .getAllProducts()
            .map { it.toProductDto() }
            .toList()
    }
}
