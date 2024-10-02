package fd.f1.f1dataandroid.service

import com.google.gson.Gson
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
            val urlWithParams = buildUrl(urlString, queryItems)

            val url = URL(urlWithParams)

            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = "GET"

                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw WSError.InvalidStatusCode(responseCode)
                }

                // Récupère les données et décode
                inputStream.bufferedReader().use { reader ->
                    val responseData = reader.readText()
                    return@withContext decodeJson(responseData, T::class.java)
                }
            } ?: throw WSError.InvalidURL
        }

        /**
         * Fonction utilitaire pour décoder une chaîne JSON en un objet.
         */
        fun <T> decodeJson(jsonString: String, classOfT: Class<T>): T {
            return Gson().fromJson(jsonString, classOfT)
        }

        /**
         * Construit une URL avec des paramètres de requête.
         */
        fun buildUrl(baseUrl: String, queryItems: Map<String, String>): String {
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
            return urlBuilder.toString()
        }
    }
}