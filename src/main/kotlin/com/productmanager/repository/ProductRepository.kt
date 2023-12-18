package com.productmanager.repository

import com.productmanager.ProductEntity
import com.productmanager.ProductManagerDatabase
import com.productmanager.dto.NewProductRequest
import com.productmanager.dto.ProductType
import com.productmanager.mapper.toProductEntity

class ProductRepository(database: ProductManagerDatabase) {
    private val queries = database.productManagerDatabaseQueries

    fun findByProductType(productType: ProductType): List<ProductEntity> {
        return queries.findByProductType(productType).executeAsList()
    }

    fun findById(id: String): ProductEntity {
        return queries.findById(id).executeAsOne()
    }

    fun insertProduct(product: NewProductRequest) {
        queries.insertProduct(product.toProductEntity())
    }

    fun getAllProducts(): List<ProductEntity> {
        return queries.getAllProducts().executeAsList()
    }
}