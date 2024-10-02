package fd.f1.f1dataandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fd.f1.f1dataandroid.model.RaceUnfolding
import fd.f1.f1dataandroid.model.UnfoldingType
import fd.f1.f1dataandroid.service.RaceControlService
import kotlinx.coroutines.launch

sealed class RaceControlState {
    data object NotAvailable : RaceControlState()
    data object Loading : RaceControlState()
    data class Success<T>(val data: T? = null) : RaceControlState()
    data class Failed(val error: Throwable) : RaceControlState()
}

class RaceControlViewModel: ViewModel() {
    var state: RaceControlState = RaceControlState.NotAvailable
        private set

    private val service = RaceControlService()

    fun getAllRadiosFrom(session: Int, driver: Int?) {
        state = RaceControlState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllTeamRadiosFrom(session = session, driver = driver)
                state = RaceControlState.Success(data = result)
            } catch (error: Throwable) {
                state = RaceControlState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getAllControlsFromSession(session: Int) {
        state = RaceControlState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllControlsInSession(session = session)
                state = RaceControlState.Success(data = result)
            } catch (error: Throwable) {
                state = RaceControlState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getSessionUnfolding(session: Int) {
        state = RaceControlState.Loading

        viewModelScope.launch {
            try {
                val controls = service.fetchAllControlsInSession(session = session)
                val radios = service.fetchAllTeamRadiosFrom(session = session, driver = null)

                val combined = (controls.map { RaceUnfolding(it.date, UnfoldingType.RC(it)) } +
                                radios.map { RaceUnfolding(it.date, UnfoldingType.Radio(it)) })
                        .sortedBy { it.date } // Trier par date

                state = RaceControlState.Success(data = combined)
            } catch (error: Throwable) {
                state = RaceControlState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}