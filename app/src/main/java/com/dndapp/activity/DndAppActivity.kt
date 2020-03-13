package com.dndapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.dndapp.R
import com.dndapp.extensions.hideSoftwareKeyboard
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.android.ext.android.inject

class DndAppActivity : AppCompatActivity() {

    private val navigationViewModel by inject<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, _, _ ->
            hideSoftwareKeyboard()
        }
        navigationViewModel.observe(this, navController)
    }
}
