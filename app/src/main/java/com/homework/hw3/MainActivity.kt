package com.homework.hw3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.homework.hw3.data.BottomNavItem
import com.homework.hw3.ui.components.BottomNavigationBar
import com.homework.hw3.ui.screens.CartScreen
import com.homework.hw3.ui.screens.CatalogueScreen
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Text("Online Shop")
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Online Shop") })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationGraph(navController = navController, modifier = Modifier, paddingValues)
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Catalogue.route) {
        composable(BottomNavItem.Catalogue.route) {
            CatalogueScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
        composable(BottomNavItem.Cart.route) {
            CartScreen()
        }
    }
}