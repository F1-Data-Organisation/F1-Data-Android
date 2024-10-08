package fd.f1.f1dataandroid.service

import fd.f1.f1dataandroid.model.Driver
import fd.f1.f1dataandroid.model.LapData
import fd.f1.f1dataandroid.model.Meeting
import fd.f1.f1dataandroid.model.Session

class MeetingService {
    private val items = mutableMapOf<String, String>()

    suspend fun fetchAllMeetings(): List<Meeting> {
        return RequestWS.decodeAPIInfoList(route = "meetings", queryItems = items)
    }

    suspend fun fetchMeetingByKey(key: Int): Meeting {
        return RequestWS.decodeAPIInfo(route = "meetings/by-key/${key}", queryItems = items)
    }

    suspend fun fetchSessionsByMeeting(key: Int): List<Session> {
        return RequestWS.decodeAPIInfoList(route = "meetings/sessions/mt-key/${key}", queryItems = items)
    }

    suspend fun fetchSessionByKey(key: Int): Session {
        return RequestWS.decodeAPIInfo(route = "meetings/sessions/se-key/${key}", queryItems = items)
    }

    suspend fun fetchSessionClassification(key: Int): List<Driver> {
        return RequestWS.decodeAPIInfoList(route = "meetings/session-classification/${key}", queryItems = items)
    }

    suspend fun fetchFastestLap(session: Int): LapData {
        return RequestWS.decodeAPIInfo(route = "meetings/sessions/fastest-lap/${session}", queryItems = items)
    }
}