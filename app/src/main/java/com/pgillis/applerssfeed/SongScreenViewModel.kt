package com.pgillis.applerssfeed

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import com.pgillis.applerssfeed.models.Song
import com.pgillis.applerssfeed.service.RSSDatabase
import com.pgillis.applerssfeed.service.RSSRemoteRepo
import kotlinx.coroutines.flow.Flow

class SongScreenViewModel(
    rssRemoteRepo: RSSRemoteRepo,
    private val rssDatabase: RSSDatabase
): ViewModel() {

    init {
        rssRemoteRepo.songs()
    }

    val songs: Flow<List<Song>>
        get() = rssDatabase.songDao().getSongs()

    fun openSongIntent(uriHandler: UriHandler, song: Song) {
        uriHandler.openUri(song.url)
    }
}