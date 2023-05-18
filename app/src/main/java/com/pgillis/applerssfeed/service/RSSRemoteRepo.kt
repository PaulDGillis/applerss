package com.pgillis.applerssfeed.service

import android.util.Log
import com.pgillis.applerssfeed.models.Feed
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class RSSRemoteRepo(private val client: OkHttpClient, private val dao: SongDAO) {
    companion object {
        private const val TAG = "RSSRemoteRepo"

        val json = Json { ignoreUnknownKeys = true }
        val stringToFeedMapSerializer: KSerializer<Map<String, Feed>> = serializer()
    }

    fun songs() {
        val request = Request.Builder()
            .url("https://rss.applemarketingtools.com/api/v2/us/music/most-played/10/songs.json")
            .build()

        enqueue(request) { body ->
            val songs = json.decodeFromString(stringToFeedMapSerializer, body)
                    .getValue("feed")
                    .results

            dao.insertSongs(songs)
        }
    }

    private fun enqueue(request: Request, onResponseBlock: (String) -> Unit) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        Log.e(TAG, "Unexpected code $response")
                    } else {
                        response.body?.string()?.let(onResponseBlock)
                    }
                }
            }
        })
    }
}