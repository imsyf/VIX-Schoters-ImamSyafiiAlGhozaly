package im.syf.nuice.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.syf.nuice.data.remote.NewsApiService
import im.syf.nuice.data.remote.response.CollectionArticlesDto
import kotlinx.coroutines.launch

class HomeViewModel(
    newsApiService: NewsApiService,
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    init {
        viewModelScope.launch {
            try {
                val res = newsApiService.getTopHeadlines()
                _state.value = State(res)
            } catch (e: Exception) {
                Log.d("blah", "Error: ${e.message}")
            }
        }
    }

    data class State(
        val response: CollectionArticlesDto,
    )
}
