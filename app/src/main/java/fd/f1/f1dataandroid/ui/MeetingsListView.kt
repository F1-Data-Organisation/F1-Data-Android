package fd.f1.f1dataandroid.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.extensions.f1Regular
import fd.f1.f1dataandroid.model.Meeting
import fd.f1.f1dataandroid.ui.components.CountryFlag
import fd.f1.f1dataandroid.ui.components.ErrorView
import fd.f1.f1dataandroid.viewmodel.MeetingState
import fd.f1.f1dataandroid.viewmodel.MeetingViewModel

@SuppressLint("DefaultLocale")
@Composable
fun MeetingsListView(meetingViewModel: MeetingViewModel = viewModel()) {
    var firstLoad by remember { mutableStateOf(true) }
    val state by meetingViewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is MeetingState.NotAvailable -> { Text("Aucune valeur disponible") }
            is MeetingState.Loading -> {
                CircularProgressIndicator(
                    trackColor = MaterialTheme.colorScheme.primary
                )
            }
            is MeetingState.Failed -> {
                val error = (state as MeetingState.Failed).error
                ErrorView(error = error)
            }
            is MeetingState.Success<*> -> {
                val data = (state as MeetingState.Success<*>).data
                if (data is List<*>) {
                    LazyColumn {
                        itemsIndexed(data) { index, meeting ->
                            if (meeting is Meeting) {
                                //val destination = { MeetingDetailView(meeting = meeting) }

                                val modifier = Modifier
                                    .fillMaxWidth()
                                /*.animateContent(
                                    visible = !firstLoad.value,
                                    delayMillis = (index * 500)
                                )*/

                                Row(
                                    modifier = Modifier
                                        //.clickable(onClick = destination)
                                        .then(modifier)
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = String.format("%02d", index + 1),
                                        style = TextStyle().f1Bold(24.sp),
                                        modifier = Modifier.width(40.dp)
                                    )

                                    CountryFlag(code = meeting.countryName, width = 30)

                                    Column {
                                        Text(
                                            text = meeting.name,
                                            style = TextStyle().f1Bold(20.sp)
                                        )
                                        Text(
                                            text = meeting.location,
                                            style = TextStyle().f1Regular(16.sp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    SideEffect {
        if (firstLoad) {
            meetingViewModel.getAllMeetings()
            firstLoad = false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeetingListPreview() {
    MeetingsListView()
}