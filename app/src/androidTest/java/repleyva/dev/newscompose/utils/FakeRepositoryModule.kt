package repleyva.dev.newscompose.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import repleyva.dev.newscompose.di.RepositoryModule
import repleyva.dev.newscompose.model.NewsEntity
import repleyva.dev.newscompose.provider.NewsProvider
import repleyva.dev.newscompose.repository.NewsRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository(provider: NewsProvider): NewsRepository =
        object : NewsRepository {

            val news = arrayListOf(
                NewsEntity("Title1", "Content1", "Author1", "Url1", "Url1"),
                NewsEntity("Title2", "Content2", "Author2", "Url2", "Url2")
            )

            override suspend fun getTopHeadLines(country: String): List<NewsEntity> = news

            override fun getNew(title: String): NewsEntity = news.first()

        }
}