package com.pgillis.applerssfeed.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("feed")
data class Feed(val results: List<Song>)

@Entity
@Serializable
data class Song(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val artistName: String = "",
    @SerialName("artworkUrl100")
    val artworkUrl: String = "",
    val url: String = ""
)