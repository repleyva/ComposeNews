package repleyva.dev.newscompose.repository

import repleyva.dev.newscompose.common.ERROR
import repleyva.dev.newscompose.common.ERROR_KEY_INVALID
import repleyva.dev.newscompose.common.ERROR_KEY_MISSING
import repleyva.dev.newscompose.exceptions.InvalidApiKeyException
import repleyva.dev.newscompose.exceptions.MissingApiKeyException
import repleyva.dev.newscompose.model.NewsApiResponse
import repleyva.dev.newscompose.model.NewsEntity
import repleyva.dev.newscompose.provider.NewsProvider
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsProvider: NewsProvider
) : NewsRepository {

    private var news: List<NewsEntity> = emptyList()

    override suspend fun getTopHeadLines(
        country: String
    ): List<NewsEntity> {
        val apiResponse = newsProvider.getTopHeadLines(country).body()
        validateApiError(apiResponse)
        news = apiResponse?.articles ?: emptyList()
        return news
    }

    override fun getNew(
        title: String
    ): NewsEntity = news.first { it.title == title }

    private fun validateApiError(apiResponse: NewsApiResponse?) {
        if (apiResponse?.status == ERROR) {
            when (apiResponse.code) {
                ERROR_KEY_MISSING -> throw MissingApiKeyException()
                ERROR_KEY_INVALID -> throw InvalidApiKeyException()
                else -> throw Exception()
            }
        }
    }

}