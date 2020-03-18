package com.dndapp.character.list

import androidx.lifecycle.ViewModel
import com.dndapp.R
import com.dndapp.model.character.CharacterRepository
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel

class CharacterListViewModel(
    characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val characters = characterRepository.getCharacters()

    fun onCreate() = navigationViewModel.navigate(Destination(R.id.fragment_character_create))
}