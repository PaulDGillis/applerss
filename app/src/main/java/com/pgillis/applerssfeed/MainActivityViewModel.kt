package com.pgillis.applerssfeed

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgillis.applerssfeed.models.Song
import com.pgillis.applerssfeed.service.AppleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val appleService: AppleService = AppleService.init()
): ViewModel() {

    val songs: Flow<List<Song>>
        get() = appleService.songs()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            songs.collect { Log.e("Test", it.firstOrNull()?.name ?: "") }
        }
    }

    fun openSongIntent(song: Song) = Intent(Intent.ACTION_VIEW, Uri.parse(song.url))
}