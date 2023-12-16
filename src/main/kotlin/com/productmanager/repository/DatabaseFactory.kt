package com.productmanager.repository

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.productmanager.ProductEntity
import com.productmanager.ProductManagerDatabase
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

fun createDriver(type: Type): SqlDriver {
    return when (type) {
        Type.POSTGRES -> createPostgresDriver()
        Type.SQLITE -> createSqliteDriver()
    }
}

private fun createSqliteDriver(): SqlDriver {
    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    ProductManagerDatabase.Schema.create(driver).value
    return driver
}

private fun createPostgresDriver(): SqlDriver {
    val dataSource: DataSource = HikariDataSource(
        HikariConfig().apply {
            driverClassName = "org.h2.Driver"
            jdbcUrl = "jddbc:h2:file:"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })

    val driver = dataSource.asJdbcDriver()
    ProductManagerDatabase.Schema.create(driver)
    return driver
}

enum class Type {
    POSTGRES, SQLITE
}

fun createDatabase(driver: SqlDriver): ProductManagerDatabase {
    return ProductManagerDatabase.invoke(
        driver,
        ProductEntity.Adapter(EnumColumnAdapter())
    )
}