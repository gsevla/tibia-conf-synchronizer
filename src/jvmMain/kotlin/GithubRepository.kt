import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class GithubRepository {
    private val httpClient = HttpClient(CIO) {
        defaultRequest {
            url("https://api.github.com")
        }
    }

    suspend fun listUserGists(username: String): String {
        val url = "/users/${username}/gists"
        println(url)
        return httpClient.get(url).body()
    }
}