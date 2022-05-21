package repleyva.dev.newscompose.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import repleyva.dev.newscompose.ui.theme.NewsComposeTheme
import repleyva.dev.newscompose.utils.ConnectionState
import repleyva.dev.newscompose.utils.connectivityState

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

@Composable
fun CircularProgress() =
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }

@ExperimentalCoroutinesApi
@Composable
fun ConnectivityStatus() {
    // This will cause re-composition on every network state change
    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available

    if (isConnected) {
        Toast.makeText(LocalContext.current, "conectado", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(LocalContext.current, "Sin conexión", Toast.LENGTH_SHORT).show()
    }
}