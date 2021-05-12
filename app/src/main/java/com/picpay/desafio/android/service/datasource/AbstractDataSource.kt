package com.picpay.desafio.android.service.datasource

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class AbstractDataSource {
    protected var retrofit: Retrofit = createRetrofitService()

    companion object {
        val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
        private fun createRetrofitService(): Retrofit {

            val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            httpClient.addInterceptor {
                val request = it.request()
                val requestBuilder = request.newBuilder()
                val builder = requestBuilder.build()
                it.proceed(builder)
            }

            val converter = GsonConverterFactory.create(GsonBuilder().create())

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converter)
                .client(httpClient.build())
                .build()
        }
    }
}
