package com.dndapp.model.character

import com.dndapp.model.race.Race
import com.dndapp.utils.HasId
import java.io.Serializable

data class Character(
    val name: String,
    val race: Race,
    override val id: Long = 0
) : HasId<Long>, Serializable