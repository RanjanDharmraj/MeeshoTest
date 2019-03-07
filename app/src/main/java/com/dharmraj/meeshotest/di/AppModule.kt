package com.dharmraj.meeshotest.di

import android.app.Application
import com.dharmraj.meeshotest.BuildConfig
import com.dharmraj.meeshotest.network.ApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideOkHttpCache(application: Application): Cache {
            val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
            return Cache(application.cacheDir, cacheSize)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideGson(): Gson {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            return gsonBuilder.create()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideOkHttpClient(cache: Cache): OkHttpClient {
            val client = OkHttpClient.Builder()
            client.cache(cache)
            return client.build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_ENDPPOINT)
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideApiService(retrofit: Retrofit) : ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}