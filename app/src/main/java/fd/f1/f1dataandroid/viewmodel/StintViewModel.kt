package fd.f1.f1dataandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fd.f1.f1dataandroid.service.StintService
import kotlinx.coroutines.launch

sealed class StintState {
    data object NotAvailable : StintState()
    data object Loading : StintState()
    data class Success<T>(val data: T? = null) : StintState()
    data class Failed(val error: Throwable) : StintState()
}

class StintViewModel: ViewModel() {
    var state: StintState = StintState.NotAvailable
        private set

    private val service = StintService()

    fun getAllStintsFromDriverAndSession(driver: Int, session: Int) {
        state = StintState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchStintsDriverInSession(driver = driver, session = session)
                state = StintState.Success(data = result)
            } catch (error: Throwable) {
                state = StintState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}