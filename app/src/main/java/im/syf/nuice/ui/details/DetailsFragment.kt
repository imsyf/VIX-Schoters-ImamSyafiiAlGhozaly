package im.syf.nuice.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import coil.load
import im.syf.nuice.NuiceApp
import im.syf.nuice.data.local.Bookmark
import im.syf.nuice.data.remote.response.ArticleDto
import im.syf.nuice.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as NuiceApp
                DetailsViewModel(args.article.url, app.bookmarkDao)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isArticleBookmarked.observe(viewLifecycleOwner) {
            binding.bookmark.isVisible = !it
            binding.remove.isVisible = it
        }
        binding.render(args.article)
    }

    private fun FragmentDetailsBinding.render(article: ArticleDto) {
        thumbnail.load(article.urlToImage) { crossfade(true) }
        title.text = article.title
        author.text = article.author
        date.text = args.timeAgo
        description.text = article.description
        content.text = article.content

        open.setOnClickListener {
            val url = Uri.parse(article.url)
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }

        bookmark.setOnClickListener {
            viewModel.bookmark(Bookmark.fromArticle(article))
        }

        remove.setOnClickListener {
            viewModel.remove(Bookmark.fromArticle(article))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
