package com.homework.hw3.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.homework.hw3.api.ShopController
import com.homework.hw3.data.CatalogueItem
import com.homework.hw3.data.CatalogueItemType

@Composable
fun CatalogueScreen() {
    val shopController = ShopController()
    val itemType = rememberSaveable { CatalogueItemType.Hair }
    val (catalogueItems, setCatalogueItems) = rememberSaveable { mutableStateOf(emptyArray<CatalogueItem>()) }

    LaunchedEffect(Unit) {
        val items = shopController.getItems(itemType)
        setCatalogueItems(items)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Catalogue Screen")
        LazyColumn {
            items(items = catalogueItems) { item ->
                Text(item.name)
            }
        }
    }
}