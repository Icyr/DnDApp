package com.dndapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dndapp.data.Character
import com.dndapp.data.CharacterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel(private val characterDao: CharacterDao) : ViewModel() {

    val characters: LiveData<List<Character>>
        get() = characterDao.getCharacters()

    fun createCharacter(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            characterDao.insert(Character(0, name))
        }
    }

}