package repleyva.dev.newscompose.ui.flow.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import repleyva.dev.newscompose.R
import repleyva.dev.newscompose.model.NewsEntity

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    onDetail: (NewsEntity) -> Unit,
) {
    val newsList by viewModel.getTopHeadLines().observeAsState(initial = emptyList())
    NewsListComponent(onDetail, newsList)
}

@Composable
private fun NewsListComponent(
    onDetail: (NewsEntity) -> Unit,
    news: List<NewsEntity>
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        }) {
        LazyColumn {
            items(news) { new ->
                NewsItem(new, onDetail)
            }
        }
    }
}

@Composable
fun NewsItem(
    new: NewsEntity,
    onDetail: (NewsEntity) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onDetail(new) },
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                painter = rememberImagePainter(
                    data = new.urlToImage,
                    builder = {
                        placeholder(R.drawable.ic_launcher_background)
                        error(R.drawable.ic_launcher_background)
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(Modifier.padding(8.dp)) {
                Text(new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(new.content.orEmpty(), maxLines = 3)
            }
        }

    }
}
