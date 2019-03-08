package com.dharmraj.meeshotest.di

import com.dharmraj.meeshotest.BuildConfig
import com.dharmraj.meeshotest.network.ApiService
import com.dharmraj.meeshotest.network.repository.PullRequestRepository
import com.dharmraj.meeshotest.network.repository.PullRequestRepositoryImpl
import com.dharmraj.meeshotest.network.source.PullRequestService
import com.dharmraj.meeshotest.network.source.Service
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun provideApiService(pullRequestService: PullRequestService): Service

    @Binds
    abstract fun provideRepository(repository: PullRequestRepositoryImpl): PullRequestRepository

    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        fun provideOkhttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .connectTimeout( 10L, TimeUnit.SECONDS)
                .readTimeout( 10L, TimeUnit.SECONDS)

            val logging = HttpLoggingInterceptor()
            // set your desired log level
            if (BuildConfig.DEBUG) {
                // development build
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                // production build
                logging.level = HttpLoggingInterceptor.Level.NONE
            }
            builder.addInterceptor(logging)

            return builder.build()
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        @JvmStatic
        @Provides
        @Singleton
        fun provideAuthApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    }
}