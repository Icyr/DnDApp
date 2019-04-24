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

fun getBonus(value: Int): Int = when (value) {
    0, 1 -> -5
    2, 3 -> -4
    4, 5 -> -3
    6, 7 -> -2
    8, 9 -> -1
    10, 11 -> 0
    12, 13 -> 1
    14, 15 -> 2
    16, 17 -> 3
    18, 19 -> 4
    20, 21 -> 5
    22, 23 -> 6
    24, 25 -> 7
    26, 27 -> 8
    28, 29 -> 9
    30 -> 10
    else -> -5
}