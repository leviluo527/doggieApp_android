package com.example.doggieapp_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.doggieapp_android.ui.InboxScreen
import com.example.doggieapp_android.ui.MoreScreen
import com.example.doggieapp_android.ui.ServiceDetailScreen
import com.example.doggieapp_android.ui.ServicesScreen
import com.example.doggieapp_android.ui.YourPetsScreen
import com.example.doggieapp_android.ui.theme.DoggieApp_androidTheme

sealed class Screen(val route: String) {
    sealed class BottomBarScreen(route: String, val label: String, val icon: Int) : Screen(route) {
        object Inbox : BottomBarScreen("inbox", "Inbox", R.drawable.ic_inbox)
        object Services : BottomBarScreen("services", "Services", R.drawable.ic_services)
        object YourPets : BottomBarScreen("your_pets", "Your Pets", R.drawable.ic_pets)
        object More : BottomBarScreen("more", "More", R.drawable.ic_more)
    }

    object ServiceDetail : Screen("service_detail/{serviceName}/{serviceDescription}")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.BottomBarScreen.Inbox,
        Screen.BottomBarScreen.Services,
        Screen.BottomBarScreen.YourPets,
        Screen.BottomBarScreen.More,
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(painterResource(id = screen.icon), contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.BottomBarScreen.Services.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.BottomBarScreen.Inbox.route) { InboxScreen() }
            composable(Screen.BottomBarScreen.Services.route) { ServicesScreen(navController) }
            composable(Screen.BottomBarScreen.YourPets.route) { YourPetsScreen() }
            composable(Screen.BottomBarScreen.More.route) { MoreScreen() }
            composable(
                route = Screen.ServiceDetail.route,
                arguments = listOf(
                    navArgument("serviceName") { type = NavType.StringType },
                    navArgument("serviceDescription") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                ServiceDetailScreen(
                    serviceName = backStackEntry.arguments?.getString("serviceName") ?: "",
                    serviceDescription = backStackEntry.arguments?.getString("serviceDescription") ?: ""
                )
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoggieApp_androidTheme {
                AppNavigation()
            }
        }
    }
}
