package com.dndapp.model.abilityscores.room

import androidx.room.Dao
import androidx.room.Query
import com.dndapp.model.BaseDao

@Dao
interface AbilityScoresDao : BaseDao<AbilityScoresEntity> {

    @Query("SELECT * FROM ability_scores")
    fun getAbilityScores(): List<AbilityScoresEntity>
}