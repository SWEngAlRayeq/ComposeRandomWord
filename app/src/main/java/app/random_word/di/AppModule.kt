package app.random_word.di

import app.random_word.data.remote.RandomWordApi
import app.random_word.data.repo_impl.RandomWordRepositoryImpl
import app.random_word.domain.repo.RandomWordRepository
import app.random_word.domain.usecase.GetRandomWordUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://random-word-api.herokuapp.com/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(logging).build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RandomWordApi =
        retrofit.create(RandomWordApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: RandomWordApi): RandomWordRepository =
        RandomWordRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetRandomWordUseCase(repository: RandomWordRepository) =
        GetRandomWordUseCase(repository)


}