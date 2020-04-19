package com.dndapp.character.create.name

import androidx.lifecycle.ViewModel
import com.dndapp.R
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel

class CharacterCreateNameViewModel(private val navigationViewModel: NavigationViewModel) : ViewModel() {

    fun onNext() = navigationViewModel.navigate(Destination(R.id.fragment_character_create_race))
}