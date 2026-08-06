package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(onShowGreeting = { typedName ->
                // pass the name by creating a Greeting route object
                navController.navigate(route = Greeting(userName = typedName))
            })
        }
        composable<Greeting> { backStackEntry ->
            // rebuild the typed Greeting object on this screen
            val greeting: Greeting = backStackEntry.toRoute()
            GreetingScreen(
                userName = greeting.userName,
                onBack = { navController.popBackStack()} )
        }
    }
}

class NavHost {
}