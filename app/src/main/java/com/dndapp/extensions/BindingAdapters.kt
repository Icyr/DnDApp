package com.dndapp.extensions

import android.view.View
import androidx.core.view.isInvisible
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("invisible")
    @JvmStatic
    fun visible(view: View, value: Boolean) {
        view.isInvisible = value
    }
}