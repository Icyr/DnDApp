package com.dndapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val characterClass: CharacterClass,
    val level: Int
)