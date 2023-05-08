package com.pgillis.applerssfeed

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import com.pgillis.applerssfeed.models.Song
import com.pgillis.applerssfeed.service.AppleService
import kotlinx.coroutines.flow.Flow

class SongScreenViewModel(
    private val appleService: AppleService = AppleService.init()
): ViewModel() {

    val songs: Flow<List<Song>>
        get() = appleService.songs()

    fun openSongIntent(uriHandler: UriHandler, song: Song) {
        uriHandler.openUri(song.url)
    }
}