package com.pgillis.applerssfeed

import android.app.Application
import androidx.compose.ui.platform.UriHandler
import com.pgillis.applerssfeed.models.Song
import com.pgillis.applerssfeed.service.BaseViewModel
import kotlinx.coroutines.flow.Flow

class SongScreenViewModel(application: Application): BaseViewModel(application) {

    init {
        rssRemoteRepo.songs()
    }

    val songs: Flow<List<Song>>
        get() = rssDatabase.songDao().getSongs()

    fun openSongIntent(uriHandler: UriHandler, song: Song) {
        uriHandler.openUri(song.url)
    }
}