package fd.f1.f1dataandroid.ui.components.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fd.f1.f1dataandroid.extensions.f1Regular

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        NavigationBarItem(
            alwaysShowLabel = true,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = {
                Text(
                    text = "Accueil",
                    style = TextStyle().f1Regular(12.sp),
                )
            },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
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
            selected = currentRoute == "meetings-list",
            onClick = {
                navController.navigate("meetings-list") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavPreview() {
    BottomNavigationBar(navController = rememberNavController())
}