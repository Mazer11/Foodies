package ru.mazer.foodies.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.mazer.foodies.domain.remote.DefaultRemoteRepository
import ru.mazer.foodies.domain.remote.RemoteApi
import ru.mazer.foodies.domain.usecases.GetCategoriesUseCase
import ru.mazer.foodies.domain.usecases.GetProductsUseCase
import ru.mazer.foodies.domain.usecases.GetTagsUseCase
import ru.mazer.foodies.domain.usecases.complex.RemoteUseCases
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun mockWebServer(): MockWebServer = MockWebServer()

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
    fun provideRetrofit(
        mockWebServer: MockWebServer,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit): RemoteApi = retrofit.create(RemoteApi::class.java)

    @Provides
    fun provideRetrofitRepository(api: RemoteApi): DefaultRemoteRepository {
        return DefaultRemoteRepository(api)
    }

    //Provides use cases for retrofit API
    //Property app needs only while server doesn't exist
    @Provides
    fun provideRemoteUseCases(
        repository: DefaultRemoteRepository,
        @ApplicationContext context: Context
    ): RemoteUseCases = RemoteUseCases(
        getTagsUseCase = GetTagsUseCase(repository, context),
        getCategoriesUseCase = GetCategoriesUseCase(repository, context),
        getProductsUseCase = GetProductsUseCase(repository, context)
    )

}

