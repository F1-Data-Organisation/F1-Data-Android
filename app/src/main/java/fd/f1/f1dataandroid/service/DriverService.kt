package fd.f1.f1dataandroid.service

import fd.f1.f1dataandroid.model.Driver
import fd.f1.f1dataandroid.model.LapData

class DriverService {
    suspend fun fetchAllDrivers(session: Int?): List<Driver> {
        val queryItems = mutableMapOf<String, String>()
        session?.let { num ->
            queryItems["session"] = num.toString()
        }

        return RequestWS.decodeAPIInfo(route = "drivers", queryItems = queryItems)
    }

    suspend fun fetchDriverByNumber(driver: Int, session: Int?): Driver {
        val queryItems = mutableMapOf<String, String>()
        session?.let { num ->
            queryItems["session"] = num.toString()
        }

        return RequestWS.decodeAPIInfo(route = "drivers/by-number/${driver}", queryItems = queryItems)
    }

    suspend fun findDriverRacePace(session: Int, driver: Int): List<LapData> {
        return RequestWS.decodeAPIInfo(route = "drivers/race-pace-session/${session}/${driver}", queryItems = mutableMapOf<String, String>())
    }
}