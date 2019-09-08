package com.android.freelance.wonders.data.network

import com.android.freelance.wonders.data.network.response.WondersResponse
import retrofit2.http.GET
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://api.myjson.com/"
interface ApiWonders {
    @GET("bins/13g69v")
    fun getWondersList(): Observable<WondersResponse>//suspend

    companion object {

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiWonders {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiWonders::class.java)
        }
    }
}