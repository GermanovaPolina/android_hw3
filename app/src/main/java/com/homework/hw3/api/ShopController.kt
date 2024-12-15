package com.homework.hw3.api

import com.homework.hw3.data.CatalogueItem
import com.homework.hw3.data.CatalogueItemType
import kotlinx.coroutines.delay

class ShopController {
    private val delayMs = 0L
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

    suspend fun getCardItems(ids: Array<String>): Array<CatalogueItem> {
        delay(delayMs)

        val items = mutableListOf<CatalogueItem>()
        ids.forEach { id ->
            hairItems.map {
                if (it.id == id) {
                    items.add(it)
                }
            }

            nailsItems.map {
                if (it.id == id) {
                    items.add(it)
                }
            }
        }

        return items.toTypedArray()
    }
}