package fd.f1.f1dataandroid.ui

import fd.f1.f1dataandroid.R
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.ui.components.BottomNavigationBar

/**
 * Composable function that represents the main screen of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = TextStyle().f1Bold(16.sp)
                    )
                },
                /*navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },*/
            )
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        // Le padding ici est appliqué au contenu du NavHost pour qu'il se place correctement entre la topBar et la bottomBar
        NavHost(
            navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding) // Applique le padding
        ) {
            composable("home") { HomeView() }
            composable("meetings-list") { MeetingsListView() }
        }
    }
}