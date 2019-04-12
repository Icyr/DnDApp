package com.dndapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dndapp.data.CharacterDao
import com.dndapp.data.entity.Character
import com.dndapp.data.entity.CharacterClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(private val characterDao: CharacterDao) : ViewModel() {

    val characters: LiveData<List<Character>>
        get() = characterDao.getCharacters()

    suspend fun createCharacter(
        name: String, characterClass: CharacterClass, level: Int,
        strength: Int, dexterity: Int, constitution: Int,
        intelligence: Int, wisdom: Int, charisma: Int
    ): Long = withContext(Dispatchers.IO) {
        characterDao.insert(
            Character(
                0,
                name, characterClass, level,
                strength, dexterity, constitution,
                intelligence, wisdom, charisma
            )
        )
    }

    fun deleteCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            characterDao.delete(character)
        }
    }

}