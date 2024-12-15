package com.homework.hw3.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.homework.hw3.utils.CartManager

@Composable
fun CartScreen(paddingValues: PaddingValues) {
    val context = LocalContext.current
    val cartManager = CartManager(context)
    val cart = rememberSaveable { cartManager.getCart() }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(cart.toString())
    }
}
