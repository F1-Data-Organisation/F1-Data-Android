package fd.f1.f1dataandroid.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fd.f1.f1dataandroid.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

sealed class WSError : Exception() {
    data object InvalidURL : WSError() {
        private fun readResolve(): Any = InvalidURL
    }

    data class InvalidStatusCode(val code: Int) : WSError()
}

class RequestWS {
    companion object {
        suspend inline fun <reified T> decodeAPIInfo(
            route: String,
            queryItems: Map<String, String>
        ): T = withContext(Dispatchers.IO) {

            // Construit l'URL avec les paramètres de requête
            val urlString = "${BuildConfig.URL_WS}/$route"
            val url = buildUrl(urlString, queryItems)

            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = "GET"

                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw WSError.InvalidStatusCode(responseCode)
                }

                // Récupère les données et décode
                inputStream.bufferedReader().use { reader ->
                    val responseData = reader.readText()
                    return@withContext Gson().fromJson(responseData, T::class.java)
                }
            } ?: throw WSError.InvalidURL
        }

        suspend inline fun <reified T> decodeAPIInfoList(
            route: String,
            queryItems: Map<String, String>
        ): List<T> = withContext(Dispatchers.IO) {

            // Construit l'URL avec les paramètres de requête
            val urlString = "${BuildConfig.URL_WS}/$route"
            val url = buildUrl(urlString, queryItems)

            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = "GET"

                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw WSError.InvalidStatusCode(responseCode)
                }

                // Récupère les données et décode
                inputStream.bufferedReader().use { reader ->
                    val responseData = reader.readText()
                    val type = object : TypeToken<List<T>>() {}
                    return@withContext Gson().fromJson(responseData, type)
                }
            } ?: throw WSError.InvalidURL
        }

        /**
         * Construit une URL avec des paramètres de requête.
         */
        fun buildUrl(baseUrl: String, queryItems: Map<String, String>): URL {
            val urlBuilder = StringBuilder(baseUrl)
            if (queryItems.isNotEmpty()) {
                urlBuilder.append("?")
                // Construit les paires clé-valeur pour les paramètres de requête
                queryItems.entries.joinToString("&") {
                    "${it.key}=${it.value}"
                }.let {
                    urlBuilder.append(it)
                }
            }
            return URL(urlBuilder.toString())
        }
    }
}