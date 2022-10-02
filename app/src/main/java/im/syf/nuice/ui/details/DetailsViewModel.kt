package im.syf.nuice.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import im.syf.nuice.data.local.Bookmark
import im.syf.nuice.data.local.BookmarkDao
import kotlinx.coroutines.launch

class DetailsViewModel(
    url: String,
    private val bookmarkDao: BookmarkDao,
) : ViewModel() {

    val isArticleBookmarked: LiveData<Boolean> = bookmarkDao.isBookmarked(url).asLiveData()

    fun bookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            bookmarkDao.insert(bookmark)
        }
    }

    fun remove(bookmark: Bookmark) {
        viewModelScope.launch {
            bookmarkDao.delete(bookmark)
        }
    }
}
