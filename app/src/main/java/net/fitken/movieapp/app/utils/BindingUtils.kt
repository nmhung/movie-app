package net.fitken.movieapp.app.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import net.fitken.domain.model.Genre
import net.fitken.movieapp.R

@BindingAdapter("movieImgUrl")
fun ImageView.loadImgUrl(movieImgUrl: String?) {
    Glide.with(this)
        .load(movieImgUrl)
        .centerCrop()
        .placeholder(R.drawable.ic_movie)
        .into(this)
}

@BindingAdapter("genres")
fun ChipGroup.setItem(genres: List<Genre>?) {
    if (genres == null) return
    removeAllViews()
    genres.forEach {
        val chip = Chip(context)
        chip.text = it.name
        chip.chipStrokeWidth = 2F
        chip.chipStrokeColor = ColorStateList.valueOf(Color.GRAY)
        chip.setChipBackgroundColorResource(android.R.color.white)
        addView(chip)
    }
}

@BindingAdapter("movieRunTime")
fun TextView.showMovieRunTime(runtime: Int?) {
    runtime?.let {
        val hour = it / 60
        val min = it % 60
        text = String.format("%dh %dm", hour, min)
    }
}