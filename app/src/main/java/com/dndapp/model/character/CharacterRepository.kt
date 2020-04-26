package com.dndapp.model.character

import com.dndapp.model.Repository
import com.dndapp.model.background.BackgroundRepository
import com.dndapp.model.character.room.CharacterDao
import com.dndapp.model.character.room.CharacterEntity
import com.dndapp.model.character.room.JoinedCharacter
import com.dndapp.model.race.RaceRepository

class CharacterRepository(
    private val characterDao: CharacterDao,
    raceRepository: RaceRepository,
    backgroundRepository: BackgroundRepository
) : Repository<Character> {

    private val transformation: (input: JoinedCharacter) -> Character = {
        val race = raceRepository.transformation.invoke(it.race)
        val background = backgroundRepository.transformation.invoke(it.background)
        Character(it.character.name, race, background, it.character.id)
    }

    override fun loadAll(): List<Character> = characterDao.getCharacters().map(transformation)

    fun add(character: Character) {
        character.apply {
            characterDao.insert(CharacterEntity(name, race.id, background.id))
        }
    }
}