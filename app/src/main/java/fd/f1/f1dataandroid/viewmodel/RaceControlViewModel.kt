package fd.f1.f1dataandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fd.f1.f1dataandroid.model.RaceUnfolding
import fd.f1.f1dataandroid.model.UnfoldingType
import fd.f1.f1dataandroid.service.RaceControlService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class RaceControlState {
    data object NotAvailable : RaceControlState()
    data object Loading : RaceControlState()
    data class Success<T>(val data: T? = null) : RaceControlState()
    data class Failed(val error: Throwable) : RaceControlState()
}

class RaceControlViewModel: ViewModel() {
    private var _state = MutableStateFlow<RaceControlState>(RaceControlState.NotAvailable)
    val state: StateFlow<RaceControlState> = _state.asStateFlow()

    private val service = RaceControlService()

    fun getAllRadiosFrom(session: Int, driver: Int?) {
        _state.value = RaceControlState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllTeamRadiosFrom(session = session, driver = driver)
                _state.value = RaceControlState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = RaceControlState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getAllControlsFromSession(session: Int) {
        _state.value = RaceControlState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllControlsInSession(session = session)
                _state.value = RaceControlState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = RaceControlState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getSessionUnfolding(session: Int) {
        _state.value = RaceControlState.Loading

        viewModelScope.launch {
            try {
                val controls = service.fetchAllControlsInSession(session = session)
                val radios = service.fetchAllTeamRadiosFrom(session = session, driver = null)

                val combined = (controls.map { RaceUnfolding(it.date, UnfoldingType.RC(it)) } +
                                radios.map { RaceUnfolding(it.date, UnfoldingType.Radio(it)) })
                        .sortedBy { it.date } // Trier par date

                _state.value = RaceControlState.Success(data = combined)
            } catch (error: Throwable) {
                _state.value = RaceControlState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}