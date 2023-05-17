package com.pgillis.applerssfeed.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pgillis.applerssfeed.models.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDAO {
    @Query("SELECT * FROM song")
    fun getSongs(): Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(songs: List<Song>)

    @Query("DELETE FROM song")
    fun deleteSongs()
}