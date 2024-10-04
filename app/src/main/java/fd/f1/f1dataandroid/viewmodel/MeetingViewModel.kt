package fd.f1.f1dataandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fd.f1.f1dataandroid.service.MeetingService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class MeetingState {
    data object NotAvailable : MeetingState()
    data object Loading : MeetingState()
    data class Success<T>(val data: T? = null) : MeetingState()
    data class Failed(val error: Throwable) : MeetingState()
}

class MeetingViewModel: ViewModel() {
    private var _state = MutableStateFlow<MeetingState>(MeetingState.NotAvailable)
    val state: StateFlow<MeetingState> = _state.asStateFlow()

    private val service = MeetingService()

    fun getAllMeetings() {
        _state.value = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllMeetings()
                _state.value = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = MeetingState.Failed(error)
                println(error)
            }
        }
    }

    fun getMeetingByKey(key: Int) {
        _state.value = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchMeetingByKey(key = key)
                _state.value = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getAllSessionsFromMeeting(key: Int) {
        _state.value = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchSessionsByMeeting(key = key)
                _state.value = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getSessionByKey(key: Int) {
        _state.value = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchSessionByKey(key = key)
                _state.value = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getSessionClassification(key: Int) {
        _state.value = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchSessionClassification(key = key)
                _state.value = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getFastestLap(session: Int) {
        _state.value = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchFastestLap(session = session)
                _state.value = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                _state.value = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}