package com.example.musicapp.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {

    @GET(MusicNetwork.ENDPOINT)
    fun getMusicByFilters(
        @Query("term") musicType: String,
        @Query("amp;media") mediaType: String = "music",
        @Query("amp;entity") entityType: String = "song",
        @Query("amp;limit") limitType: Int = 50,
    ): Call<MusicResponse>
}

