package fd.f1.f1dataandroid.service

import fd.f1.f1dataandroid.model.Stint

class StintService {
    suspend fun fetchStintsDriverInSession(driver: Int, session: Int): List<Stint> {
        return RequestWS.decodeAPIInfo(route = "drivers/session-stints/${driver}/${session}", queryItems = mutableMapOf<String, String>())
    }
}