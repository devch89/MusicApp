package com.example.musicapp.view

import retrofit2.http.Query

interface Communicator {
    fun doSearch(musicType: String, mediaType: String, entityType: String, limitType: Int)
}
