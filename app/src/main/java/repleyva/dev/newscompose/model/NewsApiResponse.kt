package repleyva.dev.newscompose.model

data class NewsApiResponse(
    val status: String? = null,
    val code: String? = null,
    val articles: List<NewsEntity>? = null
)
