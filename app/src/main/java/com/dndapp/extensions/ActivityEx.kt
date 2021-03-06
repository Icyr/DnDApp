package com.dndapp.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

fun AppCompatActivity.hideSoftwareKeyboard(view: View) {
    ContextCompat.getSystemService(this, InputMethodManager::class.java)?.hideSoftInputFromWindow(
        view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun AppCompatActivity.hideSoftwareKeyboard() {
    val view = currentFocus ?: View(this)
    hideSoftwareKeyboard(view)
}

fun AppCompatActivity.showSoftwareKeyboard(view: View) {
    ContextCompat.getSystemService(this, InputMethodManager::class.java)?.showSoftInput(
        view, InputMethodManager.SHOW_IMPLICIT
    )
}

fun AppCompatActivity.showSoftwareKeyboard() {
    val view = currentFocus ?: View(this)
    showSoftwareKeyboard(view)
}