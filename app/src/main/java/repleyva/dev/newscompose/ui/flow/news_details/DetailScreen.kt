package repleyva.dev.newscompose.ui.flow.news_details

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import repleyva.dev.newscompose.R
import repleyva.dev.newscompose.common.EMPTY_STRING
import repleyva.dev.newscompose.model.NewsEntity
import repleyva.dev.newscompose.ui.composable.CircularProgress

@Composable
fun DetailScreen(
    viewModel: NewsDetailViewModel = hiltViewModel(),
    newsTitle: String,
    onBack: () -> Unit
) {
    val new by viewModel.getNewsByTitle(newsTitle).observeAsState(initial = null)
    DetailScreenComponent(newsTitle, new, onBack)
}

@Composable
private fun DetailScreenComponent(
    newsTitle: String,
    new: NewsEntity?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = newsTitle, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.secondary,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = EMPTY_STRING
                        )
                    }
                }
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        new?.let {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column {
                    TopCardNews(it)
                    BottomCardNews(it)
                }
            }
        } ?: run { CircularProgress() }
    }
}

@Composable
fun TopCardNews(new: NewsEntity) {
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
}

@Composable
fun BottomCardNews(new: NewsEntity) {
    Column(
        Modifier.padding(8.dp)
    ) {
        var seeMore by remember { mutableStateOf(false) }
        val context = LocalContext.current
        Text(new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(8.dp))
        Text(new.content ?: EMPTY_STRING)
        Box(Modifier.size(8.dp))
        Button(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            onClick = { seeMore = !seeMore }) {
            Text("Ver mas...", color = MaterialTheme.colors.secondary)
        }
        Spacer(modifier = Modifier.size(8.dp))
        if (seeMore) WebViewPage(new.url)
    }
}

@Composable
fun WebViewPage(url: String) {
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        backEnabled = view.canGoBack()
                    }
                }
                settings.javaScriptEnabled = true

                loadUrl(url)
                webView = this
            }
        }, update = {
            webView = it
        })

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }

}