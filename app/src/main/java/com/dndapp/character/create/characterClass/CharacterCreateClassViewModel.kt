package com.dndapp.character.create.characterClass

import androidx.lifecycle.ViewModel
import com.dndapp.R
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel

class CharacterCreateClassViewModel(
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    fun onBack() = navigationViewModel.navigate(Back())

    fun onNext() = navigationViewModel.navigate(Destination(R.id.fragment_character_create_background))

    fun onGoToList() = navigationViewModel.navigate(Destination(R.id.fragment_character_class_list))
}