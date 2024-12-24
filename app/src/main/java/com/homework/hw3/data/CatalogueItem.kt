package com.homework.hw3.data

import kotlinx.serialization.Serializable

@Serializable
data class CatalogueItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
): java.io.Serializable