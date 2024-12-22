package com.homework.hw3.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart

sealed class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    data object Catalogue : BottomNavItem(Routes.catalogue, "Каталог", Icons.Outlined.ShoppingBag)
    data object Profile : BottomNavItem(Routes.profile, "Профиль", Icons.Outlined.Person)
    data object Cart : BottomNavItem(Routes.cart, "Корзина", Icons.Outlined.ShoppingCart)
}