package com.dndapp.model.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dndapp.utils.HasId
import java.io.Serializable
import java.util.*

@Entity
data class Character(
    val name: String,
    val race: String,
    @PrimaryKey override val id: String = UUID.randomUUID().toString()
) : HasId<String>, Serializable