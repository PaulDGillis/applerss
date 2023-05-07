package com.pgillis.applerssfeed

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.pgillis.applerssfeed.models.Song
import com.pgillis.applerssfeed.service.AppleService
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(
    private val appleService: AppleService = AppleService.init()
): ViewModel() {

    val songs: Flow<List<Song>>
        get() = appleService.songs()

    fun openSongIntent(context: Context, song: Song) {
        startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(song.url)), null)
    }
}