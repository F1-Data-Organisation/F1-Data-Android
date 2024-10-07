package fd.f1.f1dataandroid.ui.components.navbar

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import fd.f1.f1dataandroid.R
import fd.f1.f1dataandroid.extensions.f1Bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavbar(
    @StringRes currentScreen: String,
    canNavBack: Boolean,
    navigateUp: () -> Unit,
    navMoreApps: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentScreen,
                style = TextStyle().f1Bold(16.sp)
            )
        },
        navigationIcon = {
            if (canNavBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Retour"
                    )
                }
            }
        },
        actions = {
            if (!canNavBack) {
                IconButton(onClick = navMoreApps) {
                    Image(
                        painter = painterResource(id = R.drawable.apps),
                        contentDescription = "Toutes les applications F1®",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                    )
                }
            }
        }
    )
}