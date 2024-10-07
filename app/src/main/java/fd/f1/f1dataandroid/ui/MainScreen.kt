package fd.f1.f1dataandroid.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fd.f1.f1dataandroid.R
import fd.f1.f1dataandroid.ui.components.AppTabView
import fd.f1.f1dataandroid.ui.components.navbar.TopNavbar

/**
 * Composable function that represents the main screen of the application.
 */
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = getCurrentScreen(backStackEntry)

    Scaffold(
        topBar = {
            TopNavbar(
                currentScreen = currentScreen,
                canNavBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                navMoreApps = { navController.navigate("more-apps") }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "tab",
            modifier = Modifier.padding(innerPadding) // Applique le padding
        ) {
            composable("tab") { AppTabView(navController = navController) }
            composable("more-apps") { MoreAppsView() }
            composable("meeting/{myData}") { backStackEntry ->
                MeetingView(backStackEntry)
            }
        }
    }
}

@Composable
fun getCurrentScreen(backStackEntry: NavBackStackEntry?): String {
    val route = backStackEntry?.destination?.route
    return if (route == null || route == "tab") {
        stringResource(id = R.string.app_name)
    } else {
        route
    }
}
