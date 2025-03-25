package com.example.bai1


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.NavController

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "homeScreen") {
        composable("homeScreen") { HomeScreen(navController) }
        composable("listScreen") { ListScreen(navController) }
        composable("detailScreen/{text}") { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: "No Data"
            DetailScreen(navController, text)
        }
    }
}


