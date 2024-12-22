package com.homework.hw3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.homework.hw3.data.CardType
import com.homework.hw3.data.CatalogueItem
import com.homework.hw3.ui.components.AddToCartButton
import com.homework.hw3.ui.theme.BaseColor
import com.homework.hw3.ui.theme.PriceFontColor
import com.homework.hw3.ui.theme.White
import com.homework.hw3.utils.CartManager

@Composable
fun ItemScreen(item: CatalogueItem) {
    val context = LocalContext.current
    val cartManager = CartManager(context)

    val (cart, setCart) = remember { mutableStateOf(cartManager.getCart()) }

    val addToCard = { id: String, delta: Int ->
        setCart(cartManager.updateCart(id, delta))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = rememberAsyncImagePainter(item.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(336.dp)
                .background(White)
                .padding(16.dp)
                .fillMaxWidth()

        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.description
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${item.price} ₽",
                    color = PriceFontColor,
                    fontSize = 6.em,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
                AddToCartButton(
                    item.id,
                    cart[item.id] ?: 0,
                    addToCard,
                    CardType.Catalogue
                )
            }
        }

    }
}
