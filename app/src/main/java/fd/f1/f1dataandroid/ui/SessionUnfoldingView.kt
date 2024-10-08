package fd.f1.f1dataandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import fd.f1.f1dataandroid.model.RaceControl
import fd.f1.f1dataandroid.model.RaceUnfolding
import fd.f1.f1dataandroid.model.TeamRadio
import fd.f1.f1dataandroid.model.UnfoldingType
import fd.f1.f1dataandroid.ui.components.ErrorView
import fd.f1.f1dataandroid.ui.components.audio.TeamRadioPlayerView
import fd.f1.f1dataandroid.viewmodel.*

@Composable
fun SessionUnfoldingView(
    raceControlVM: RaceControlViewModel = viewModel(),
    key: Int
) {
    val state by raceControlVM.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is RaceControlState.NotAvailable -> { Text("Aucune valeur disponible") }
            is RaceControlState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
            is RaceControlState.Failed -> {
                val error = (state as RaceControlState.Failed).error
                ErrorView(error = error)
            }
            is RaceControlState.Success<*> -> {
                val data = (state as RaceControlState.Success<*>).data
                if (data is List<*>) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        itemsIndexed(data) { _, unf ->
                            if(unf is RaceUnfolding) {
                                when (unf.item) {
                                    is UnfoldingType.RC -> {
                                        RaceControlView(
                                            control = unf.item.control,
                                            modifier = Modifier
                                                .padding(start = 5.dp)
                                                .padding(end = 40.dp)
                                        )
                                    }
                                    is UnfoldingType.Radio -> {
                                        TeamRadioPlayerView(
                                            radio = unf.item.radio,
                                            modifier = Modifier
                                                .padding(start = 40.dp)
                                                .padding(end = 5.dp)
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

    LaunchedEffect(Unit) {
        if (state !is RaceControlState.Success<*>) {
            raceControlVM.getSessionUnfolding(session = key)
        }
    }
}