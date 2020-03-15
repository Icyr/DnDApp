package com.dndapp.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dndapp.extensions.hideSoftwareKeyboard
import com.dndapp.extensions.showSoftwareKeyboard
import com.dndapp.utils.SingleLiveEvent

class SoftKeyboardViewModel {

    private val command = SingleLiveEvent<SoftKeyboardCommand>()

    fun show() {
        command.value = SoftKeyboardCommand.SHOW
    }

    fun hide() {
        command.value = SoftKeyboardCommand.HIDE
    }

    fun observe(owner: AppCompatActivity) {
        command.observe(owner, Observer {
            when (it) {
                SoftKeyboardCommand.SHOW -> owner.showSoftwareKeyboard()
                SoftKeyboardCommand.HIDE -> owner.hideSoftwareKeyboard()
                else -> throw IllegalStateException("SoftKeyboardCommand can not be null")
            }
        })
    }
}

enum class SoftKeyboardCommand {
    SHOW,
    HIDE
}