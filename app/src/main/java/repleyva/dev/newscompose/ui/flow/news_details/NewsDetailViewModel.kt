package repleyva.dev.newscompose.ui.flow.news_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import repleyva.dev.newscompose.model.NewsEntity
import repleyva.dev.newscompose.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    /**
     * Events
     */

    private val _news = MutableLiveData<NewsEntity>()

    /**
     * Actions
     */

    fun getNewsByTitle(title: String): MutableLiveData<NewsEntity> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = newsRepository.getNew(title)
            _news.postValue(news)
        }
        return _news
    }

}