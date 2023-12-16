package com.productmanager.repository

import com.productmanager.repository.entity.Products
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.*

object DatabaseSingleton {
    fun init(config: ApplicationConfig) {
        val driverClassName = config.property("storage.driverClassName").getString()
        val dbFilePath = config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
            File(it).canonicalFile.absolutePath
        } ?: ""
        val jdbcURL = config.property("storage.jdbcURL").getString() + dbFilePath
        val database = Database.connect(createHikariDataSource(url = jdbcURL, driver = driverClassName))

        transaction(database) {
            SchemaUtils.create(Products)
        }
    }

    // TODO learn coroutines
    suspend fun <T> dbQuery(block: suspend () -> T):
            T = newSuspendedTransaction(Dispatchers.IO) {
        block()
    }

    private fun createHikariDataSource(
        url: String,
        driver: String
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })
}