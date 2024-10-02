package fd.f1.f1dataandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fd.f1.f1dataandroid.service.MeetingService
import kotlinx.coroutines.launch

sealed class MeetingState {
    data object NotAvailable : MeetingState()
    data object Loading : MeetingState()
    data class Success<T>(val data: T? = null) : MeetingState()
    data class Failed(val error: Throwable) : MeetingState()
}

class MeetingViewModel: ViewModel() {
    var state: MeetingState = MeetingState.NotAvailable
        private set

    private val service = MeetingService()

    fun getAllMeetings() {
        state = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchAllMeetings()
                state = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                state = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getMeetingByKey(key: Int) {
        state = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchMeetingByKey(key = key)
                state = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                state = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getAllSessionsFromMeeting(key: Int) {
        state = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchSessionsByMeeting(key = key)
                state = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                state = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getSessionByKey(key: Int) {
        state = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchSessionByKey(key = key)
                state = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                state = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getSessionClassification(key: Int) {
        state = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchSessionClassification(key = key)
                state = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                state = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }

    fun getFastestLap(session: Int) {
        state = MeetingState.Loading

        viewModelScope.launch {
            try {
                val result = service.fetchFastestLap(session = session)
                state = MeetingState.Success(data = result)
            } catch (error: Throwable) {
                state = MeetingState.Failed(error)
                println(error.localizedMessage)
            }
        }
    }
}