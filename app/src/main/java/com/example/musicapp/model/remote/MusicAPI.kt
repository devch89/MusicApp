package com.example.musicapp.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {

    /*
1. Create an API interface
2. Define the HTTP Verbs in that interface
3. Define Base URL, Endpoints
4. Create the Retrofit Object
5. Create the API Interface from the Retrofit Object
 */

    //baseurl https://itunes.apple.com/
    //search
    // ?term=rock
    // &amp;media=music
    // &amp;entity=song
    // &amp;limit=50


    @GET(MusicNetwork.ENDPOINT)
    fun getMusicByFilters(
        @Query("term") musicType: String,
        @Query("amp;media") mediaType: String = "music",
        @Query("amp;entity") entityType: String = "song",
        @Query("amp;limit") limitType: Int = 50,
    ): Call<MusicResponse>
}

