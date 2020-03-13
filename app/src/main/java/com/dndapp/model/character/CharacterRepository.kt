package com.dndapp.model.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CharacterRepository {

    private val _characters = MutableLiveData(
        listOf(
            Character("1", "Character #1"),
            Character("2", "Character #2"),
            Character("3", "Character #3"),
            Character("4", "Character #4")
        )
    )
    val characters: LiveData<List<Character>>
        get() = _characters

    fun addCharacter(name: String) {
        val lastId = _characters.value?.last()?.id?.toInt() ?: 0
        _characters.value = _characters.value.orEmpty().toMutableList().apply {
            add(Character("${lastId + 1}", name))
        }.toList()
    }
}