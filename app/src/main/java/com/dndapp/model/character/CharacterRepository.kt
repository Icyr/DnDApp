package com.dndapp.model.character

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterRepository {

    @Query("SELECT * FROM character")
    fun getCharacters(): LiveData<List<Character>>

    @Insert
    fun addCharacter(character: Character)
}
