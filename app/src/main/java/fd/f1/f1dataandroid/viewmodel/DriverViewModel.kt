package fd.f1.f1dataandroid.viewmodel

import fd.f1.f1dataandroid.service.DriverService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class DriverState {
    data object NotAvailable : DriverState()
    data object Loading : DriverState()
    data class Success<T>(val data: T? = null) : DriverState()
    data class Failed(val error: Throwable) : DriverState()
}

class DriverViewModel: ViewModel() {
    var state: DriverState = DriverState.NotAvailable
        private set

    private val service = DriverService()

    fun getAllDrivers(session: Int?) {
        state = DriverState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllDrivers(session = session)
                state = DriverState.Success(data = result)
            } catch (error: Throwable) {
                state = DriverState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getDriverByNumber(number: Int, session: Int?) {
        state = DriverState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchDriverByNumber(driver = number, session = session)
                state = DriverState.Success(data = result)
            } catch (error: Throwable) {
                state = DriverState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getDriverRacePace(session: Int, driver: Int) {
        state = DriverState.Loading

        viewModelScope.launch {
            try {
                val result = service.findDriverRacePace(session = session, driver = driver)
                state = DriverState.Success(data = result)
            } catch (error: Throwable) {
                state = DriverState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}