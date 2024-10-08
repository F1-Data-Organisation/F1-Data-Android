package fd.f1.f1dataandroid.viewmodel

import fd.f1.f1dataandroid.service.DriverService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DriverState {
    data object NotAvailable : DriverState()
    data object Loading : DriverState()
    data class Success<T>(val data: T? = null) : DriverState()
    data class Failed(val error: Throwable) : DriverState()
}

class DriverViewModel: ViewModel() {
    private var _state = MutableStateFlow<DriverState>(DriverState.NotAvailable)
    val state: StateFlow<DriverState> = _state.asStateFlow()

    private val service = DriverService()

    fun getAllDrivers(session: Int?) {
        _state.value = DriverState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllDrivers(session = session)
                _state.value = DriverState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = DriverState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getDriverByNumber(number: Int, session: Int?) {
        _state.value = DriverState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchDriverByNumber(driver = number, session = session)
                _state.value = DriverState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = DriverState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getDriverRacePace(session: Int, driver: Int) {
        _state.value = DriverState.Loading

        viewModelScope.launch {
            try {
                val result = service.findDriverRacePace(session = session, driver = driver)
                _state.value = DriverState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = DriverState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}