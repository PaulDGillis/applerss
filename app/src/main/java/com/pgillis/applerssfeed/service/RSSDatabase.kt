package com.pgillis.applerssfeed.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pgillis.applerssfeed.models.Song

@Database(entities = [Song::class], version = 1)
abstract class RSSDatabase : RoomDatabase() {
    abstract fun songDao(): SongDAO
}
