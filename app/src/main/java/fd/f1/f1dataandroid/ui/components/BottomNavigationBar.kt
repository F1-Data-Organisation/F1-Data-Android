package fd.f1.f1dataandroid.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fd.f1.f1dataandroid.extensions.f1Regular

/**
 * Composable function that represents the bottom navigation bar of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            alwaysShowLabel = true,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = {
                Text(
                    text = "Accueil",
                    style = TextStyle().f1Regular(12.sp),
                )
            },
            selected = selectedItem == 0,
            onClick = {
                selectedItem = 0
                navController.navigate("home") {
                    navController.graph.startDestinationRoute?.let { route -> popUpTo(route) { saveState = true } }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            alwaysShowLabel = true,
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Meetings list") },
            label = {
                Text(
                    text = "Meetings 2024",
                    style = TextStyle().f1Regular(12.sp),
                )
            },
            selected = selectedItem == 1,
            onClick = {
                selectedItem = 1
                navController.navigate("meetings-list") {
                    navController.graph.startDestinationRoute?.let { route -> popUpTo(route) { saveState = true } }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}