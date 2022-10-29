package com.example.musicapp.model.remote

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicResponse(
    val items: Int,
    val results: List<MusicItem>
) : Parcelable


@Parcelize
data class MusicItem(
    val artistName: String,
    val collectionName: String,
    val artwork: String,
    val trackPrice: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    companion object : Parceler<MusicItem> {

        override fun MusicItem.write(parcel: Parcel, flags: Int) {
            parcel.writeString(artistName)
            parcel.writeString(collectionName)
            parcel.writeString(artwork)
            parcel.writeString(trackPrice)
        }

        override fun create(parcel: Parcel): MusicItem {
            return MusicItem(parcel)
        }
    }
}