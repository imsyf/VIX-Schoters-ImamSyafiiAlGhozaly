package im.syf.nuice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import im.syf.nuice.NuiceApp
import im.syf.nuice.R
import im.syf.nuice.databinding.LayoutEpoxyBinding
import im.syf.nuice.ext.timeAgo
import im.syf.nuice.topHeadline

class HomeFragment : Fragment() {

    private var _binding: LayoutEpoxyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as NuiceApp
                HomeViewModel(app.newsApiService)
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
        setupOptionsMenu()

        viewModel.state.observe(viewLifecycleOwner) {
            binding.epoxy.withModels {
                for (article in it.response.articles) {
                    val timeAgo = article.publishedAt.timeAgo

                    topHeadline {
                        id(article.url)
                        article(article)
                        timeAgo(timeAgo)
                        onClick { model, parentView, clickedView, position ->
                            val directions =
                                HomeFragmentDirections.toDetailsFragment(article, timeAgo)
                            findNavController().navigate(directions)
                        }
                    }
                }
            }
        }
    }

    private fun setupOptionsMenu() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_on_home, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return menuItem.onNavDestinationSelected(findNavController())
                }
            },
            viewLifecycleOwner, Lifecycle.State.RESUMED
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
