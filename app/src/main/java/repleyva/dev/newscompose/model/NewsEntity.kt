package repleyva.dev.newscompose.model

data class NewsEntity(
    val title: String,
    val content: String?,
    val author: String?,
    val url: String,
    val imageUrl: String
)
