package com.dndapp.viewmodel

import androidx.lifecycle.ViewModel
import com.dndapp.data.CharacterDao
import com.dndapp.data.entity.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterViewViewModel(private val characterDao: CharacterDao) : ViewModel() {

    suspend fun getCharacter(id: Long): Character = withContext(Dispatchers.IO) {
        characterDao.getCharacter(id)
    }

}