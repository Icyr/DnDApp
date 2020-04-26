package com.dndapp.extensions

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("visible")
    @JvmStatic
    fun visible(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.INVISIBLE
    }
}