package com.pgillis.applerssfeed.service

import android.app.Application
import androidx.room.Room
import okhttp3.OkHttpClient

interface AppContainer {
    val rssRemoteRepo: RSSRemoteRepo
    val rssDatabase: RSSDatabase
}

class AppContainerImpl(application: Application): AppContainer {
    private val okHttpClient = OkHttpClient()

    override val rssDatabase = Room.databaseBuilder(
        application,
        RSSDatabase::class.java, "rss-music-database"
    ).build()

    override val rssRemoteRepo = RSSRemoteRepo(okHttpClient, rssDatabase.songDao())
}