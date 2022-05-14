package repleyva.dev.newscompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import repleyva.dev.newscompose.common.URL_API
import repleyva.dev.newscompose.common.URL_BASE
import repleyva.dev.newscompose.provider.NewsProvider
import repleyva.dev.newscompose.repository.NewsRepository
import repleyva.dev.newscompose.repository.NewsRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository(provider: NewsProvider): NewsRepository =
        NewsRepositoryImpl(provider)

}