package com.dndapp.model.character

import com.dndapp.model.abilityscores.AbilityScores
import com.dndapp.model.background.Background
import com.dndapp.model.characterClass.CharacterClass
import com.dndapp.model.race.Race
import com.dndapp.utils.HasId
import java.io.Serializable

data class Character(
    val name: String,
    val race: Race,
    val background: Background,
    val characterClass: CharacterClass,
    val abilityScores: AbilityScores,
    override val id: Long = 0
) : HasId<Long>, Serializable