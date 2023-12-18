package com.productmanager.utils

import kotlinx.serialization.Serializable
import java.math.BigDecimal


typealias BigDecimalJson = @Serializable(with = BigDecimalSerializer::class) BigDecimal