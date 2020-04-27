package com.dndapp.model.characterClass

import com.dndapp.model.Repository
import com.dndapp.model.characterClass.room.CharacterClassDao
import com.dndapp.model.characterClass.room.CharacterClassEntity

class CharacterClassRepository(private val characterClassDao: CharacterClassDao) : Repository<CharacterClass> {

    val transformation: (input: CharacterClassEntity) -> CharacterClass = {
        CharacterClass(it.name, it.id)
    }

    override fun loadAll(): List<CharacterClass> = characterClassDao.getCharacterClasses().map(transformation)
}