package repleyva.dev.newscompose.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import repleyva.dev.newscompose.ui.theme.NewsComposeTheme

@Composable
fun NewsApp(content: @Composable () -> Unit) =
    NewsComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }