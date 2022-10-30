package com.example.musicapp.model.remote


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicResponse(
    val items: Int,
    val results: List<MusicItem>
) : Parcelable


@Parcelize
data class MusicItem(
    val artistName: String = "",
    val trackName: String = "",
    val artworkUrl60: String = "",
    val trackPrice: Double = 0.0,
    val currency: String = "",
    val previewUrl: String = "",
) : Parcelable
