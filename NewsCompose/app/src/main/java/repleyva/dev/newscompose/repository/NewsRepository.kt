package repleyva.dev.newscompose.repository

import repleyva.dev.newscompose.model.NewsEntity

interface NewsRepository {

    suspend fun getTopHeadLines(country: String): List<NewsEntity>

    fun getNew(title: String): NewsEntity
}