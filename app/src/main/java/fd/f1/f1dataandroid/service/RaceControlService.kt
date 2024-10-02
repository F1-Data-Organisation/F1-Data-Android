package fd.f1.f1dataandroid.service

import fd.f1.f1dataandroid.model.TeamRadio
import fd.f1.f1dataandroid.model.RaceControl

class RaceControlService {
    suspend fun fetchAllTeamRadiosFrom(session: Int, driver: Int?): List<TeamRadio> {
        val queryItems = mutableMapOf<String, String>()
        driver?.let { num ->
            queryItems["driver"] = num.toString()
        }

        return RequestWS.decodeAPIInfo(route = "race-control/team-radios/session/${session}", queryItems = queryItems)
    }

    suspend fun fetchAllControlsInSession(session: Int): List<RaceControl> {
        return RequestWS.decodeAPIInfo(route = "race-control/controls/session/${session}", queryItems = mutableMapOf<String, String>())
    }
}