package im.syf.nuice.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("urlToImage")
fun ImageView.bindImage(urlToImage: String?) {
    if (urlToImage != null) {
        load(urlToImage) {
            crossfade(true)
        }
    }
}
