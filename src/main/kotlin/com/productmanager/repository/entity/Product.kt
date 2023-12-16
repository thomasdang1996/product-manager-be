package com.productmanager.repository.entity

import com.productmanager.dto.ProductType
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

data class Product(
    val id: Long,
    val name: String,
    val productType: ProductType,
    val price: BigDecimal
)

object Products : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 50)
    val productType = enumeration("productType", ProductType::class)
    val price = decimal("price",1,1)

    override val primaryKey = PrimaryKey(id)
}