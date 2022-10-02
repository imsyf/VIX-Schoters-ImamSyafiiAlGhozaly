package im.syf.nuice.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import im.syf.nuice.data.local.Bookmark
import im.syf.nuice.data.local.BookmarkDao

class BookmarkViewModel(
    bookmarkDao: BookmarkDao,
) : ViewModel() {

    val bookmarks: LiveData<List<Bookmark>> = bookmarkDao.getBookmarks().asLiveData()
}
