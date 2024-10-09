package fd.f1.f1dataandroid.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.model.Driver
import fd.f1.f1dataandroid.model.Session

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SessionNavigation(list: List<*>) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = getCurrentScreen(backStackEntry)

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(25.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (navController.previousBackStackEntry != null) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour",
                            modifier = Modifier.height(25.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = currentScreen,
                    style = TextStyle().f1Bold(16.sp),
                )

                Spacer(modifier = Modifier.weight(1f))
                if (navController.previousBackStackEntry != null) {
                    Spacer(modifier = Modifier.width(25.dp))
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "meeting-session-list",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("meeting-session-list") { MeetingSessionList(list, navController) }
            composable("session/{session_data}") { backStackEntry ->
                SessionView(backStackEntry, navController)
            }
            composable(
                route = "data/{d_data}/{s_data}",
                arguments = listOf(
                    navArgument("d_data") { type = NavType.StringType },
                    navArgument("s_data") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                DriverSessionDataView(backStackEntry)
            }
        }
    }
}