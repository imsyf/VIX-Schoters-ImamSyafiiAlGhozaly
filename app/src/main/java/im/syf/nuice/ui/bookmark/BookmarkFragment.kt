package im.syf.nuice.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import im.syf.nuice.NuiceApp
import im.syf.nuice.databinding.LayoutEpoxyBinding
import im.syf.nuice.placeholder
import im.syf.nuice.topHeadline
import im.syf.nuice.ui.home.HomeFragmentDirections

class BookmarkFragment : Fragment() {

    private var _binding: LayoutEpoxyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookmarkViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as NuiceApp
                BookmarkViewModel(app.bookmarkDao)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutEpoxyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.bookmarks.observe(viewLifecycleOwner) {
            binding.epoxy.withModels {
                for (bookmark in it) {
                    val article = bookmark.toArticle()

                    topHeadline {
                        id(article.url)
                        article(article)
                        timeAgo(bookmark.timeAgo)
                        onClick { model, parentView, clickedView, position ->
                            val directions =
                                HomeFragmentDirections.toDetailsFragment(article, bookmark.timeAgo)
                            findNavController().navigate(directions)
                        }
                    }
                }

                if (it.isEmpty()) {
                    placeholder {
                        id("empty")
                        text("Bookmark is empty")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
