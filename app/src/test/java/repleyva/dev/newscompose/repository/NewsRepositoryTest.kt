package repleyva.dev.newscompose.repository

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Test
import repleyva.dev.newscompose.exceptions.InvalidApiKeyException
import repleyva.dev.newscompose.exceptions.MissingApiKeyException
import repleyva.dev.newscompose.extensions.enqueueResponse
import repleyva.dev.newscompose.provider.NewsProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepositoryTest {

    private val mockWebServer = MockWebServer()

    private val newsProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsProvider::class.java)

    private val newsRepository = NewsRepositoryImpl(newsProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Top headlines response is correct`() {
        mockWebServer.enqueueResponse("top_headlines.json")

        runBlocking {
            val articles = newsRepository.getTopHeadLines("US")
            assertEquals(2, articles.size)
            assertEquals("Sophie Lewis", articles[0].author)
            assertEquals("KOCO Staff", articles[1].author)
        }
    }

    @Test
    fun `Api key missing exception`() {
        mockWebServer.enqueueResponse("api_key_missing.json")
        assertThrows(MissingApiKeyException::class.java) {
            runBlocking { newsRepository.getTopHeadLines("US") }
        }
    }

    @Test
    fun `invalid Api key exception`() {
        mockWebServer.enqueueResponse("api_key_invalid.json")
        assertThrows(InvalidApiKeyException::class.java) {
            runBlocking { newsRepository.getTopHeadLines("US") }
        }
    }
}