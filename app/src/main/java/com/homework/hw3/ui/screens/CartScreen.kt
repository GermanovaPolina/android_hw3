package com.homework.hw3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homework.hw3.api.ShopController
import com.homework.hw3.data.CatalogueItem
import com.homework.hw3.ui.components.CartCard
import com.homework.hw3.ui.components.CartInfo
import com.homework.hw3.ui.components.Spinner
import com.homework.hw3.ui.theme.BaseColor
import com.homework.hw3.ui.theme.SecondaryColor
import com.homework.hw3.utils.CartManager

@Composable
fun CartScreen(
    paddingValues: PaddingValues,
    onNavigateToItemScreen: (CatalogueItem) -> Unit
) {
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
        cart.values.reduceOrNull { acc, q -> acc + q } ?: 0
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
            .background(BaseColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Корзина",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (loading) {
            Spinner()
            return
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = catalogueItems) { item ->
                CartCard(
                    item = item,
                    quantity = cart[item.id] ?: 0,
                    addToCard = addToCard,
                    onNavigateToItemScreen = onNavigateToItemScreen
                )
            }

            item {
                CartInfo(totalQuantity, totalPrice)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { },
                    contentPadding = PaddingValues(8.dp),
                    shape = RoundedCornerShape(100.dp),
                    enabled = totalQuantity > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SecondaryColor,
                        contentColor = Color.Black,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Оформить заказ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = "Добавить в корзину",
                    )
                }

                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
            }
        }
    }
}
