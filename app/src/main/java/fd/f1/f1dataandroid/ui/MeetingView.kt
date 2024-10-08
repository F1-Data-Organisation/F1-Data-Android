package fd.f1.f1dataandroid.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.google.gson.Gson
import fd.f1.f1dataandroid.extensions.*
import fd.f1.f1dataandroid.model.Meeting
import fd.f1.f1dataandroid.model.Session
import fd.f1.f1dataandroid.ui.components.CountryFlag
import fd.f1.f1dataandroid.ui.components.ErrorView
import fd.f1.f1dataandroid.viewmodel.MeetingState
import fd.f1.f1dataandroid.viewmodel.MeetingViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MeetingView(entry: NavBackStackEntry) {
    val meetingViewModel: MeetingViewModel = viewModel()
    val state by meetingViewModel.state.collectAsState()
    val mData = getMeeting(entry)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        mData?.let { meeting ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    CountryFlag(code = meeting.countryName, width = 75)

                    Column {
                        Text(meeting.name, style = TextStyle().f1Bold(24.sp))
                        Text(meeting.location, style = TextStyle().f1Regular(18.sp))
                    }
                }

                Text(
                    text = "${meeting.dateStart.toDate(withHours = false)} -  ${meeting.dateEnd.toDate(withHours = false)}",
                    style = TextStyle().f1Regular(18.sp)
                )

                HorizontalDivider(modifier = Modifier.padding(top = 16.dp))
            }

            when (state) {
                is MeetingState.Loading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                is MeetingState.Failed -> { ErrorView(error = (state as MeetingState.Failed).error) }
                is MeetingState.Success<*> -> {
                    val data = (state as MeetingState.Success<*>).data
                    if (data is List<*>) {
                        SessionNavigation(data)
                    }
                }
                else -> { Text("Sessions not available") }
            }

            Spacer(modifier = Modifier.weight(1f))

            SideEffect {
                if (state !is MeetingState.Success<*>) {
                    meetingViewModel.getAllSessionsFromMeeting(meeting.key)
                }
            }
        }
    }
}

fun getMeeting(entry: NavBackStackEntry): Meeting? {
    val jsonData = entry.arguments?.getString("myData")
    val mData = jsonData?.let { Gson().fromJson(it, Meeting::class.java) }
    return mData
}
