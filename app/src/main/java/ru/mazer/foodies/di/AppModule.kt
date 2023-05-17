package ru.mazer.foodies.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMockServer(): MockWebServer = MockWebServer()

    //Provides base url. For now, here is a placeholder
    @Provides
    fun providesBaseUrl(mockWebServer: MockWebServer): HttpUrl {
        return mockWebServer.url("/")
    }

    //Specify timeouts when needed
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi, baseUrl: HttpUrl): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

}