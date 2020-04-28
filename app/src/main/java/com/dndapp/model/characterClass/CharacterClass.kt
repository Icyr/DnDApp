package com.dndapp.model.characterClass

import com.dndapp.utils.HasId
import java.io.Serializable

data class CharacterClass(
    val name: String,
    override val id: Long = 0
) : HasId<Long>, Serializable