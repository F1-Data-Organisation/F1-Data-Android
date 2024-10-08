package fd.f1.f1dataandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fd.f1.f1dataandroid.service.StintService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class StintState {
    data object NotAvailable : StintState()
    data object Loading : StintState()
    data class Success<T>(val data: T? = null) : StintState()
    data class Failed(val error: Throwable) : StintState()
}

class StintViewModel: ViewModel() {
    private var _state = MutableStateFlow<StintState>(StintState.NotAvailable)
    val state: StateFlow<StintState> = _state.asStateFlow()

    private val service = StintService()

    fun getAllStintsFromDriverAndSession(driver: Int, session: Int) {
        _state.value = StintState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchStintsDriverInSession(driver = driver, session = session)
                _state.value = StintState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = StintState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}