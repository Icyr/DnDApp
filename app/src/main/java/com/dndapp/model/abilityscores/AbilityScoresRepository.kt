package com.dndapp.model.abilityscores

import com.dndapp.model.Repository
import com.dndapp.model.abilityscores.room.AbilityScoresDao
import com.dndapp.model.abilityscores.room.AbilityScoresEntity
import com.dndapp.model.characterClass.room.CharacterClassDao
import com.dndapp.model.characterClass.room.CharacterClassEntity

class AbilityScoresRepository(private val abilityScoresDao: AbilityScoresDao) : Repository<AbilityScores> {

    val transformation: (input: AbilityScoresEntity) -> AbilityScores = {
        AbilityScores()
    }

    override fun loadAll(): List<AbilityScores> = abilityScoresDao.getAbilityScores().map(transformation)
}