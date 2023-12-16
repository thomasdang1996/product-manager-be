package com.productmanager.plugins

import com.productmanager.repository.ProductRepository
import com.productmanager.repository.Type
import com.productmanager.repository.createDatabase
import com.productmanager.repository.createDriver
import com.productmanager.service.ProductService
import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(serviceModule, repositoryModule)
    }
}
//TODO maybe to separate file?
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