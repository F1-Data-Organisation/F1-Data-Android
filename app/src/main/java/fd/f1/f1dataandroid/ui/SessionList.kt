package fd.f1.f1dataandroid.ui

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.google.gson.Gson
import fd.f1.f1dataandroid.extensions.*
import fd.f1.f1dataandroid.model.Session

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MeetingSessionList(data: List<*>, navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        for (i in data.indices) {
            val session = data[i]
            if (session is Session) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val jsonSession = Gson().toJson(session)
                            navController.navigate("session/${Uri.encode(jsonSession)}")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${session.name} (${session.dateStart.toDate(withHours = true)})",
                        style = TextStyle().f1Bold(16.sp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Go to session view",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}