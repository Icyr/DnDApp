package com.dndapp.model.character.room

import androidx.room.Embedded
import androidx.room.Relation
import com.dndapp.model.abilityscores.room.AbilityScoresEntity
import com.dndapp.model.background.room.BackgroundEntity
import com.dndapp.model.characterClass.room.CharacterClassEntity
import com.dndapp.model.race.room.RaceEntity

data class JoinedCharacter(
    @Embedded val character: CharacterEntity,
    @Relation(parentColumn = "race_id", entityColumn = "id") val race: RaceEntity,
    @Relation(parentColumn = "background_id", entityColumn = "id") val background: BackgroundEntity,
    @Relation(parentColumn = "character_class_id", entityColumn = "id") val characterClass: CharacterClassEntity
    //,
   // @Embedded(prefix = "ability_scores_id_") val abilityScores: AbilityScoresEntity
)