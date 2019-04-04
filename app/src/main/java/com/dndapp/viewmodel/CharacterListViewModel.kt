package com.dndapp.viewmodel

import androidx.lifecycle.ViewModel
import com.dndapp.data.CharacterDao

class CharacterListViewModel(val characterDao: CharacterDao) : ViewModel() {

    fun getText(): String {
        return "testtesttest"
    }

}