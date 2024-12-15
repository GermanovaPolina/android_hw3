package com.homework.hw3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.homework.hw3.api.ShopController
import com.homework.hw3.data.CatalogueItem
import com.homework.hw3.data.CatalogueItemType
import com.homework.hw3.ui.components.CatalogueCard
import com.homework.hw3.utils.CartManager

@Composable
fun CatalogueScreen(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val shopController = ShopController()
    val cartManager = CartManager(context)

    val itemType = rememberSaveable { CatalogueItemType.Hair }
    val (catalogueItems, setCatalogueItems) = rememberSaveable { mutableStateOf(emptyArray<CatalogueItem>()) }
    val (cart, setCart) = rememberSaveable { mutableStateOf(cartManager.getCart()) }

    val (loading, setLoading) = remember { mutableStateOf(false) }
    val isFirstLaunch = rememberSaveable { mutableStateOf(true) }
    val addToCard = { id: String, delta: Int ->
        setCart(cartManager.updateCart(id, delta))
    }

    LaunchedEffect(Unit) {
        if (isFirstLaunch.value) {
            isFirstLaunch.value = false
            setLoading(true)

            val items = shopController.getItems(itemType)
            setCatalogueItems(items)

            setLoading(false)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Catalogue Screen")
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.size(40.dp))
        }
        LazyColumn(
            contentPadding = PaddingValues(
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            items(items = catalogueItems) { item ->
                CatalogueCard(
                    item = item,
                    quantity = cart[item.id] ?: 0,
                    addToCard = addToCard,
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}