package com.dndapp.model.race

import com.dndapp.utils.HasId
import java.io.Serializable

data class Race(
    val name: String,
    override val id: Long = 0
) : HasId<Long>, Serializable