package to.currencyconverter

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object HttpClient {

    private var client: OkHttpClient = OkHttpClient()

    fun getClient(): OkHttpClient =
        client
}

class Client {
    lateinit var currenciesXml: String
        private set

    private val url: String = "http://www.nbp.pl/kursy/xml/LastA.xml"

    fun request() {
        val request = Request.Builder()
            .url(url)
            .build()
        val response: Response = HttpClient.getClient().newCall(request).execute()
        if (response.isSuccessful && response.body != null)
            currenciesXml = response.body!!.string()
        else
            throw Exception("No jebło ¯\\_(ツ)_/¯")
    }
}
