package repleyva.dev.newscompose.provider

import repleyva.dev.newscompose.common.API_KEY
import repleyva.dev.newscompose.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsProvider {

    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getTopHeadLines(@Query("country") country: String): Response<NewsApiResponse>

}