package repleyva.dev.newscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import repleyva.dev.newscompose.ui.composable.ConnectivityStatus
import repleyva.dev.newscompose.ui.composable.NewsApp
import repleyva.dev.newscompose.ui.routes.Navigations


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConnectivityStatus()
            NewsApp { Navigations() }
        }
    }
}