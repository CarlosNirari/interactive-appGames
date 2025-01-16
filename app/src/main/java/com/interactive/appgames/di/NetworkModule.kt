package com.interactive.appgames.di

import com.interactive.appgames.data.api.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //provideer Retrofit
    /*
    @Singleton, Permite crear una unica instancia de Retrofit, no es necesario
    consumir recursos creando multiples instancias para este caso
    @Provides, declara que una funcion sera un proveedor de un recurso
    */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        // Crear un interceptor para visualizar la url consumida
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

}