package fd.f1.f1dataandroid.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fd.f1.f1dataandroid.ui.HomeView
import fd.f1.f1dataandroid.ui.MeetingsListView
import fd.f1.f1dataandroid.ui.components.navbar.BottomNavigationBar

@Composable
fun AppTabView(navController: NavController) {
    val tabController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = tabController)
        }
    ) { innerPadding ->
        NavHost(
            tabController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding) // Applique le padding
        ) {
            composable("home") { HomeView() }
            composable("meetings-list") { MeetingsListView() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppTabViewPreview() {
    AppTabView(navController = rememberNavController())
}