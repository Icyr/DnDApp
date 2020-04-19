package com.dndapp.model.character.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.dndapp.model.BaseDao

@Dao
interface CharacterDao : BaseDao<CharacterEntity> {

    @Transaction
    @Query("SELECT * FROM character")
    fun getCharacters(): LiveData<List<JoinedCharacter>>
}
