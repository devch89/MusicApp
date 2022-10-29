package com.example.musicapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory.*
import retrofit2.create

import java.net.URL

object MusicNetwork {
    /*
1. Create an API interface
2. Define the HTTP Verbs in that interface
3. Define Base URL, Endpoints
4. Create the Retrofit Object
5. Create the API Interface from the Retrofit Object
 */


    const val BASE_URL = "https://itunes.apple.com/"
    const val ENDPOINT = "search"

    //5. create the API Interface from the Retrofit Object
    //by lazy is so it can be instantiated later and immutable,
    // we check to see if the value is initialized, 1st time it is created
    // on the second time the value is already initialized so it will just return the value, in this case BookAPI

    val musicAPI: MusicAPI by lazy {
        initRetrofit().create(MusicAPI::class.java)
    }

    //4. Create the Retrofit Object
    // we will use the base url
    private fun initRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //we are doing a fetch to the GSONconverter Class
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }


}