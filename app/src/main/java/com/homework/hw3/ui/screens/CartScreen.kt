package com.homework.hw3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.homework.hw3.ui.components.CartCard
import com.homework.hw3.ui.components.Spinner
import com.homework.hw3.utils.CartManager

@Composable
fun CartScreen(paddingValues: PaddingValues) {
    val context = LocalContext.current
    val cartManager = CartManager(context)
    val shopController = ShopController()

    val (catalogueItems, setCatalogueItems) = rememberSaveable { mutableStateOf(emptyArray<CatalogueItem>()) }
    val (cart, setCart) = remember { mutableStateOf(cartManager.getCart()) }
    val totalPrice = remember(cart, catalogueItems) {
        catalogueItems.fold(0) { acc, c ->
            acc + c.price * (cart[c.id] ?: 0)
        }
    }
    val totalQuantity = remember(cart) {
        cart.values.reduce { acc, q -> acc + q }
    }

    val (loading, setLoading) = remember { mutableStateOf(false) }
    val addToCard = { id: String, delta: Int ->
        setCart(cartManager.updateCart(id, delta))
    }

    LaunchedEffect(Unit) {
        setLoading(true)

        setCatalogueItems(shopController.getCardItems(cart.keys.toTypedArray()))

        setLoading(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cart Screen")

        if (loading) {
            Spinner()
            return
        }

        Text("Товары: $totalQuantity Сумма: $totalPrice")
        LazyColumn(
            contentPadding = PaddingValues(
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            items(items = catalogueItems) { item ->
                CartCard(
                    item = item,
                    quantity = cart[item.id] ?: 0,
                    addToCard = addToCard,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
