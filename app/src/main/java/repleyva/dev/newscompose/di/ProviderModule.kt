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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {

    @Provides
    @Named(URL_BASE)
    fun provideBaseUrl() = URL_API.toHttpUrl()

    @Provides
    @Singleton
    fun provideRetrofit(@Named(URL_BASE) baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun providerNewsProvider(retrofit: Retrofit): NewsProvider =
        retrofit.create(NewsProvider::class.java)

}