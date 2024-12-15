package com.homework.hw3.api

import com.homework.hw3.data.CatalogueItem
import com.homework.hw3.data.CatalogueItemType
import kotlinx.coroutines.delay

class ShopController {
    private val delayMs = 500L
    private val error = false

    suspend fun getItems(type: CatalogueItemType): Array<CatalogueItem> {
        delay(delayMs)

        if (error) {
            throw Exception("Network error")
        }

        return when (type) {
            CatalogueItemType.Hair -> hairItems
            CatalogueItemType.Nails -> nailsItems
            CatalogueItemType.Soap -> soapItems
        }
    }

    suspend fun getItem(id: String): CatalogueItem {
        delay(delayMs)

        hairItems.map {
            if (it.id == id) {
                return it
            }
        }

        nailsItems.map {
            if (it.id == id) {
                return it
            }
        }

        throw Exception("No such element")
    }
}