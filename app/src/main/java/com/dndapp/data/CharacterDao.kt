package com.dndapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dndapp.data.entity.Character

@Dao
interface CharacterDao : BaseDao<Character> {

    @Query("SELECT * FROM character")
    fun getCharacters(): LiveData<List<Character>>

}