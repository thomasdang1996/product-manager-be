package com.productmanager.dto

import java.util.*

enum class ProductType(val code: String) {
    PHONE("PHN"), TABLET("TBLT");
    companion object{
        fun geTypeByCode(code: String): ProductType {
            return Arrays
                .stream(ProductType.entries.toTypedArray())
                .filter { type -> type.code == code }
                .findFirst()
                .orElseThrow { RuntimeException("ProductType not found: $code") }
        }
    }
}