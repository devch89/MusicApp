package com.example.musicapp.model.remote

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MusicNetwork {

    private const val BASE_URL = "https://itunes.apple.com/"
    const val ENDPOINT = "search"

    val musicAPI: MusicAPI by lazy {
        initRetrofit().create(MusicAPI::class.java)
    }

    private fun initRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}