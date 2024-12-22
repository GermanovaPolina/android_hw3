package com.homework.hw3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.homework.hw3.data.CatalogueItemType
import com.homework.hw3.ui.components.CarouselFilter
import com.homework.hw3.ui.components.CatalogueCard
import com.homework.hw3.ui.components.Spinner
import com.homework.hw3.ui.theme.BaseColor
import com.homework.hw3.utils.CartManager

@Composable
fun CatalogueScreen(
    paddingValues: PaddingValues,
    onNavigateToItemScreen: (CatalogueItem) -> Unit
) {
    val context = LocalContext.current
    val shopController = ShopController()
    val cartManager = CartManager(context)

    val (itemType, setItemType) = rememberSaveable { mutableStateOf(CatalogueItemType.Hair) }
    val (catalogueItems, setCatalogueItems) = rememberSaveable { mutableStateOf(emptyArray<CatalogueItem>()) }
    val (cart, setCart) = remember { mutableStateOf(cartManager.getCart()) }

    val (loading, setLoading) = remember { mutableStateOf(false) }
    val isFirstLaunch = rememberSaveable { mutableStateOf(true) }
    val addToCard = { id: String, delta: Int ->
        setCart(cartManager.updateCart(id, delta))
    }
    val selectFilter: suspend (CatalogueItemType) -> Unit =  { type ->
        setItemType(type)
        setLoading(true)

        val items = shopController.getItems(type)
        setCatalogueItems(items)

        setLoading(false)
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
            .background(BaseColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Каталог",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
        CarouselFilter(
            onFilterSelected = selectFilter,
            selectedFilter = itemType,
        )
        Spacer(modifier = Modifier.height(4.dp))

        if (loading) {
            Spinner()
            return
        } else if (catalogueItems.isEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Нет товаров")
        }
        LazyColumn(
            contentPadding = PaddingValues(
                bottom = paddingValues.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = catalogueItems) { item ->
                CatalogueCard(
                    item = item,
                    quantity = cart[item.id] ?: 0,
                    addToCard = addToCard,
                    onNavigateToItemScreen = onNavigateToItemScreen
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}