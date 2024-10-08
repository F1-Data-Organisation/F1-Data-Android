package fd.f1.f1dataandroid.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.model.*
import fd.f1.f1dataandroid.ui.components.ErrorView
import fd.f1.f1dataandroid.viewmodel.*

@SuppressLint("DefaultLocale")
@Composable
fun SessionCltView(
    meetingVM: MeetingViewModel = viewModel(),
    session: Session
) {
    val state by meetingVM.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is MeetingState.NotAvailable -> { Text("Aucune valeur disponible") }
            is MeetingState.Loading -> { CircularProgressIndicator(color = MaterialTheme.colorScheme.primary) }
            is MeetingState.Failed -> {
                val error = (state as MeetingState.Failed).error
                ErrorView(error = error)
            }

            is MeetingState.Success<*> -> {
                val data = (state as MeetingState.Success<*>).data
                if (data is List<*>) {
                    Text(
                        text = "Classification (chequered flag)",
                        style = TextStyle().f1Bold(20.sp),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp)
                    ) {
                        itemsIndexed(data) { index, driver ->
                            if (driver is Driver) {
                                /*val color = if (bestLap?.driver?.number == driver.number) {
                                    Color.Gray
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }*/

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                        .clickable(onClick = {  }),
                                ) {
                                    Text(
                                        text = String.format("%02d", index + 1),
                                        style = TextStyle(/*color = color*/textAlign = TextAlign.Center).f1Bold(24.sp),
                                        modifier = Modifier.width(40.dp)
                                    )

                                    DriverListItem(driver = driver/*, color = color*/)
                                }

                                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        if (state !is MeetingState.Success<*>) {
            meetingVM.getSessionClassification(key = session.key)
        }
    }
}