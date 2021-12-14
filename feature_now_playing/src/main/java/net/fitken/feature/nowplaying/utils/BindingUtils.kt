package net.fitken.feature.nowplaying.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import net.fitken.movieapp.R

@BindingAdapter("movieImgUrl")
fun ImageView.loadImgUrl(movieImgUrl: String?) {
    Glide.with(this)
        .load(movieImgUrl)
        .centerCrop()
        .placeholder(R.drawable.ic_movie)
        .into(this)
}