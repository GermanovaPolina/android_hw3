package com.homework.hw3.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart

sealed class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    data object Catalogue : BottomNavItem("catalogue", "Каталог", Icons.AutoMirrored.Filled.List)
    data object Profile : BottomNavItem("profile", "Профиль", Icons.Filled.Person)
    data object Cart : BottomNavItem("cart", "Корзина", Icons.Filled.ShoppingCart)
}