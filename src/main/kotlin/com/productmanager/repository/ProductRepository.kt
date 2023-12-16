package com.productmanager.repository

import com.productmanager.ProductEntity
import com.productmanager.ProductManagerDatabase
import com.productmanager.dto.ProductType
import com.productmanager.dto.NewProductRequest
import java.util.*

class ProductRepository(private val database: ProductManagerDatabase) {
    fun findByProductType(productType: ProductType): List<ProductEntity> {
        return database.productManagerDatabaseQueries.findByProductType(productType).executeAsList()
    }

    // TODO fix mapping of 'UUID -> String' across the whole app
    fun findById(id: UUID): ProductEntity {
        return database.productManagerDatabaseQueries.findById(id.toString()).executeAsOne()
    }

    fun insertProduct(product: NewProductRequest) {
        database.productManagerDatabaseQueries.insertProduct(
            UUID.randomUUID().toString(),
            product.name,
            ProductType.geTypeByCode(product.productTypeCode),
            product.price.toBigDecimal()
        )
    }

    fun getAllProducts(): List<ProductEntity> {
        val allProducts = database.productManagerDatabaseQueries.getAllProducts()
        println("AS LIST: ${allProducts.executeAsList()}")
        return allProducts.executeAsList()
    }
}