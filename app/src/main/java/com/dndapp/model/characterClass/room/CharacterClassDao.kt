package com.dndapp.model.characterClass.room

import androidx.room.Dao
import androidx.room.Query
import com.dndapp.model.BaseDao

@Dao
interface CharacterClassDao : BaseDao<CharacterClassEntity> {

    @Query("SELECT * FROM characterClass")
    fun getCharacterClasses(): List<CharacterClassEntity>
}