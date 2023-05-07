package com.pgillis.applerssfeed.service

import com.pgillis.applerssfeed.models.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

class AppleService(private val client: OkHttpClient) {
    companion object {
        fun init() = AppleService(OkHttpClient())

        val json = Json { ignoreUnknownKeys = true }
        val stringToFeedMapSerializer: KSerializer<Map<String, Feed>> = serializer()
    }

    fun songs() = flow {
        val request = Request.Builder()
            .url("https://rss.applemarketingtools.com/api/v2/us/music/most-played/10/songs.json")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val songs = json.decodeFromString(stringToFeedMapSerializer, response.body!!.string()).getValue("feed").results
            emit(songs)
        }
    }.flowOn(Dispatchers.IO)
}