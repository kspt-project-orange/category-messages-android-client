package ru.spbstu.icst.categorymessages.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.spbstu.icst.categorymessages.BuildConfig

object OkHttpClientFactory {

    fun createClient(/*userIdentity: UserIdentity?*/): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
//        if (userIdentity != null) {
//            okHttpClientBuilder.addInterceptor(AuthInterceptor(userIdentity))
//        }

        return okHttpClientBuilder.build()
    }
}