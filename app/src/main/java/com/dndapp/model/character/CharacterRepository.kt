package com.dndapp.model.character

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterRepository {

    @Query("SELECT * FROM character")
    fun getCharacters(): LiveData<List<Character>>

    @Insert
    fun addCharacter(character: Character)

    @Update
    fun updateCharacter(character: Character)
}
