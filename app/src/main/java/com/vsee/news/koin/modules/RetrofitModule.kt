package com.vsee.news.koin.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vsee.news.BuildConfig
import com.vsee.news.data.remote.features.news.IFeedServiceApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    factory { createOkHttpClient() }
    single { provideAPIService<IFeedServiceApi>(get(), BuildConfig.BASE_API) }
}

fun createOkHttpClient(): OkHttpClient {
    val headersInterceptor = Interceptor { chain ->
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    val httpClient = OkHttpClient.Builder()
    httpClient.connectTimeout(1, TimeUnit.MINUTES)
    httpClient.readTimeout(1, TimeUnit.MINUTES)
    httpClient.writeTimeout(1, TimeUnit.MINUTES)

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    httpClient.interceptors().add(logging)

    httpClient.addNetworkInterceptor(headersInterceptor)
    return httpClient.build()
}

inline fun <reified T> provideAPIService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}
