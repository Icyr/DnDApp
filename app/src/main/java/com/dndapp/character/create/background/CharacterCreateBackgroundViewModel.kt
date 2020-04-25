package com.dndapp.character.create.background

import androidx.lifecycle.ViewModel
import com.dndapp.R
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel

class CharacterCreateBackgroundViewModel(
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    fun onGoToList() = navigationViewModel.navigate(Destination(R.id.fragment_background_list))

    fun onBack() = navigationViewModel.navigate(Back())
}