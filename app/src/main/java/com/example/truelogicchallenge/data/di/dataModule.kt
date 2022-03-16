package com.example.truelogicchallenge.data.di

import com.example.truelogicchallenge.data.CharacterRepositoryImpl
import com.example.truelogicchallenge.data.mapper.CharacterMapper
import com.example.truelogicchallenge.data.remotesource.CharacterRemoteSource
import com.example.truelogicchallenge.domain.CharacterRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.breakingbadapi.com/api/"

val dataModule = module {

    factory {
        Gson()
    }

    single {
        GsonConverterFactory.create(get<Gson>())
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(get<GsonConverterFactory>())
            .baseUrl(BASE_URL)
            .client(get())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        get<Retrofit>().create(CharacterRemoteSource::class.java)
    }

    single {
        CharacterMapper()
    }

    single<CharacterRepository> {
        CharacterRepositoryImpl(get(), get())
    }
}