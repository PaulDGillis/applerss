package com.pgillis.applerssfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pgillis.applerssfeed.models.Song
import com.pgillis.applerssfeed.ui.theme.MyApplicationTheme

import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "songs") {
                        composable("songs") { SongsScreen(viewModel) }
                    }

                }
            }
        }
    }
}

@Composable
fun SongsScreen(viewModel: MainActivityViewModel) {
    val songs by viewModel.songs.collectAsState(initial = emptyList())
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(songs) { song ->
            SongView(song, onClick = {
//                val context = LocalContext.current
//                viewModel.openSongIntent(context, it)
            })
        }
    }
}

@Composable
fun SongView(song: Song, modifier: Modifier = Modifier, onClick: (Song) -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick(song) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = { song.artworkUrl },
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .border(0.5.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
        )
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(text = song.name)
            Text(
                text = "by ${song.artistName}",
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SongViewPreview() {
    MyApplicationTheme {
        SongView(Song(name = "Test Name"), onClick = {})
    }
}