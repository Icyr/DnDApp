package com.dndapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.dndapp.R
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.SoftKeyboardViewModel
import org.koin.android.ext.android.inject

class DndAppActivity : AppCompatActivity() {

    private val navigationViewModel by inject<NavigationViewModel>()
    private val softKeyboardViewModel by inject<SoftKeyboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigationViewModel.observe(this, navController)
        softKeyboardViewModel.observe(this)
    }
}
