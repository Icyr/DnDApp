package com.dndapp.viewmodel

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import java.util.*

class NavigationViewModel {

    private val commands = MutableLiveData<MutableList<NavigationCommand>>(LinkedList())

    fun navigate(command: NavigationCommand) {
        commands.value = (commands.value ?: LinkedList()).apply {
            add(command)
        }
    }

    fun observe(owner: LifecycleOwner, controller: NavController) {
        commands.observe(owner) { commands ->
            commands.forEach { it.execute(controller) }
            commands.clear()
        }
    }
}

interface NavigationCommand {
    fun execute(controller: NavController)
}

data class Destination(
    @IdRes val destinationId: Int,
    val args: Bundle? = null,
    val popUpTo: PopUpTo? = null
) : NavigationCommand {
    override fun execute(controller: NavController) {
        val builder = NavOptions.Builder()
        popUpTo?.let {
            builder.setPopUpTo(it.destinationId, it.inclusive)
        }
        controller.navigate(destinationId, args, builder.build())
    }
}

data class PopUpTo(@IdRes val destinationId: Int, val inclusive: Boolean = false)

data class Back(@IdRes val destinationId: Int? = null, val inclusive: Boolean = false) : NavigationCommand {
    override fun execute(controller: NavController) {
        if (destinationId == null) {
            controller.popBackStack()
        } else {
            controller.popBackStack(destinationId, inclusive)
        }
    }
}