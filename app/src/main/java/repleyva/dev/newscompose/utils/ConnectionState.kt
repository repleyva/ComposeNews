package repleyva.dev.newscompose.utils

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}