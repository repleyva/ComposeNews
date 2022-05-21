package repleyva.dev.newscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import repleyva.dev.newscompose.ui.composable.NewsApp
import repleyva.dev.newscompose.ui.routes.Navigations


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NewsApp { Navigations() } }
    }
}