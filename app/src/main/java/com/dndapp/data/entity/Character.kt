package com.dndapp.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val characterClass: CharacterClass,
    val level: Int,
    @Embedded(prefix = "characteristic") val characteristics: Characteristics
)

data class Characteristics(
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int
)