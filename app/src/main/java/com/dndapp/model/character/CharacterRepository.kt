package com.dndapp.model.character

import androidx.lifecycle.Transformations
import com.dndapp.model.character.room.CharacterDao
import com.dndapp.model.character.room.CharacterEntity
import com.dndapp.model.character.room.JoinedCharacter
import com.dndapp.model.race.RaceRepository

class CharacterRepository(private val characterDao: CharacterDao, raceRepository: RaceRepository) {
    val transformation: (input: JoinedCharacter) -> Character = {
        Character(it.character.name, raceRepository.transformation.invoke(it.race), it.character.id)
    }
    val characters = Transformations.map(characterDao.getCharacters()) { list ->
        list.map(transformation)
    }

    fun add(character: Character) {
        character.apply {
            characterDao.insert(CharacterEntity(name, race.id))
        }
    }
}