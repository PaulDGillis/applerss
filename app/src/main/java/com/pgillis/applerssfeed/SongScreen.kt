package com.pgillis.applerssfeed

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pgillis.applerssfeed.ui.theme.MyApplicationTheme
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SongScreen(
    modifier: Modifier = Modifier,
    viewModel: SongScreenViewModel = viewModel()
) {
    val uriHandler = LocalUriHandler.current
    val songs by viewModel.songs.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(songs) { song ->
            Row(modifier = modifier
                .fillMaxWidth()
                .clickable { viewModel.openSongIntent(uriHandler, song) }
                .padding(10.dp),
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
    }
}

@Preview(showBackground = true)
@Composable
fun SongScreenPreview() {
    MyApplicationTheme {
        SongScreen()
    }
}