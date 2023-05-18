package ru.mazer.foodies.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.mazer.foodies.FoodiesApp
import ru.mazer.foodies.domain.remote.RemoteApi
import ru.mazer.foodies.domain.remote.RemoteRepository
import ru.mazer.foodies.domain.usecases.GetCategoriesUseCase
import ru.mazer.foodies.domain.usecases.GetProductsUseCase
import ru.mazer.foodies.domain.usecases.GetTagsUseCase
import ru.mazer.foodies.domain.usecases.complex.RemoteUseCases
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp(@ApplicationContext app: Context): FoodiesApp {
        return app as FoodiesApp
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
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi, app: FoodiesApp): Retrofit =
        Retrofit.Builder()
            .baseUrl(app.getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun provideCardDataApi(retrofit: Retrofit): RemoteApi =
        retrofit.create(RemoteApi::class.java)

    @Provides
    fun provideRetrofitRepository(api: RemoteApi): RemoteRepository {
        return RemoteRepository(api)
    }

    @Provides
    fun provideRemoteUseCases(repository: RemoteRepository): RemoteUseCases = RemoteUseCases(
        getTagsUseCase = GetTagsUseCase(repository),
        getCategoriesUseCase = GetCategoriesUseCase(repository),
        getProductsUseCase = GetProductsUseCase(repository)
    )

}