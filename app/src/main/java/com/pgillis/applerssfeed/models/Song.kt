package com.pgillis.applerssfeed.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("feed")
data class Feed(val results: List<Song>)

@Serializable
data class Song(
    val id: String = "",
    val name: String = "",
    val artistName: String = "",
    @SerialName("artworkUrl100")
    val artworkUrl: String = "",
    val url: String = ""
)