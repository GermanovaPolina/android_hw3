package com.homework.hw3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.homework.hw3.data.BottomNavItem
import com.homework.hw3.data.CatalogueItem
import com.homework.hw3.data.Routes
import com.homework.hw3.ui.components.BottomNavigationBar
import com.homework.hw3.ui.screens.CartScreen
import com.homework.hw3.ui.screens.CatalogueScreen
import com.homework.hw3.ui.screens.ItemScreen
import com.homework.hw3.ui.screens.PaymentScreen
import com.homework.hw3.ui.screens.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationGraph(navController = navController, paddingValues)
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Catalogue.route) {
        composable(BottomNavItem.Catalogue.route) {
            CatalogueScreen(paddingValues) { item ->
                navController.navigate(route = item)
            }
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
        composable(Routes.payment) {
            PaymentScreen(paddingValues)
        }
        composable(BottomNavItem.Cart.route) {
            CartScreen(
                paddingValues,
                onNavigateToItemScreen = { item ->
                    navController.navigate(route = item)
                },
                onNavigateToPayment = {
                    navController.navigate(Routes.payment)
                }
            )
        }
        composable<CatalogueItem> { backStackEntry ->
            val item: CatalogueItem = backStackEntry.toRoute()
            ItemScreen(item)
        }
    }
}