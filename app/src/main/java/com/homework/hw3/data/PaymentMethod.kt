package com.homework.hw3.data

typealias PaymentMethodId = Int

data class PaymentMethod(
    val id: PaymentMethodId,
    val name: String,
    val imageUrl: String,
    val digits: String,
    val isActive: Boolean
)