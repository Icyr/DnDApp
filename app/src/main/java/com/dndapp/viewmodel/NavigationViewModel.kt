package com.dndapp.viewmodel

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
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

data class Destination(@IdRes val destinationId: Int, val args: Bundle? = null) : NavigationCommand {
    override fun execute(controller: NavController) {
        controller.navigate(destinationId, args)
    }
}

data class Back(@IdRes val destinationId: Int? = null, val inclusive: Boolean = false) : NavigationCommand {
    override fun execute(controller: NavController) {
        if (destinationId == null) {
            controller.popBackStack()
        } else {
            controller.popBackStack(destinationId, inclusive)
        }
    }
}