package com.dndapp.model.character.room

import androidx.room.Embedded
import androidx.room.Relation
import com.dndapp.model.race.room.RaceEntity

data class JoinedCharacter(
    @Embedded val character: CharacterEntity,
    @Relation(parentColumn = "race_id", entityColumn = "id") val race: RaceEntity
)