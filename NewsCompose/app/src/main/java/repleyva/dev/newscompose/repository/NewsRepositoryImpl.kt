package repleyva.dev.newscompose.repository

import repleyva.dev.newscompose.model.NewsEntity

class NewsRepositoryImpl: NewsRepository {

    override suspend fun getTopHeadLines(country: String): List<NewsEntity> {
        TODO("Not yet")
    }

    override fun getNew(title: String): NewsEntity {
        TODO("Not yet")
    }

}