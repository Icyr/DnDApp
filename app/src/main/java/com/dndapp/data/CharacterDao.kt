package com.dndapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CharacterDao : BaseDao<Character> {

    @Query("SELECT * FROM character")
    fun getCharacters(): LiveData<List<Character>>

}