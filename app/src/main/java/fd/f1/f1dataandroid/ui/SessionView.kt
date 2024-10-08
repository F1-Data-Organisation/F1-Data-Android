package fd.f1.f1dataandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.google.gson.Gson
import fd.f1.f1dataandroid.model.Session
import fd.f1.f1dataandroid.ui.components.AppTabRow

@Composable
fun SessionView(entry: NavBackStackEntry) {
    val sData = getSession(entry)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        sData?.let { session ->
            AppTabRow(
                tabs = listOf("Classification", "Radios & race control", "Weather"),
                contentScreens = listOf(
                    {  },
                    {  },
                    {  }
                )
            )
        }
    }
}

fun getSession(entry: NavBackStackEntry): Session? {
    val jsonData = entry.arguments?.getString("session_data")
    val mData = jsonData?.let { Gson().fromJson(it, Session::class.java) }
    return mData
}