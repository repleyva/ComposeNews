package repleyva.dev.newscompose.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import repleyva.dev.newscompose.common.EMPTY_STRING
import repleyva.dev.newscompose.ui.flow.news.NewsScreen
import repleyva.dev.newscompose.ui.flow.news_details.DetailScreen

@Composable
fun Navigations() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME_NEWS
    ) {
        composable(Destinations.HOME_NEWS) {
            NewsScreen { new ->
                navController.navigate("${Destinations.DETAIL_NEWS}/${new.title}")
            }
        }
        composable("${Destinations.DETAIL_NEWS}/{newTitle}") {
            val search = it.arguments?.getString("newTitle") ?: EMPTY_STRING
            DetailScreen(newsTitle = search) { navController.popBackStack() }
        }
    }
}