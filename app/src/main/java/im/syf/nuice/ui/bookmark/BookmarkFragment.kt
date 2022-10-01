package im.syf.nuice.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import im.syf.nuice.R
import im.syf.nuice.databinding.PlaceholderBinding

class BookmarkFragment : Fragment() {

    private var _binding: PlaceholderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PlaceholderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textview.setText(R.string.bookmark)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
