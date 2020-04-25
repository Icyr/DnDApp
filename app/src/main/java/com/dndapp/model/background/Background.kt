package com.dndapp.model.background

import com.dndapp.utils.HasId
import java.io.Serializable

data class Background(
    val name: String,
    override val id: Long = 0
) : HasId<Long>, Serializable