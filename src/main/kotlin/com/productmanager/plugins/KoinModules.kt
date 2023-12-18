package com.productmanager.plugins

import com.productmanager.repository.ProductRepository
import com.productmanager.repository.Type
import com.productmanager.repository.createDatabase
import com.productmanager.repository.createDriver
import com.productmanager.service.ProductService
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin modules allows to create and manage singleton classes
 */
val repositoryModule: Module = module {
    factory { createDriver(Type.SQLITE) }
    single { createDatabase(driver = get()) }
    single<ProductRepository> {
        ProductRepository(database = get())
    }
}

val serviceModule: Module = module {
    single<ProductService> {
        ProductService(repository = get())
    }
}