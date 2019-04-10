package com.dndapp.data

import androidx.room.TypeConverter
import com.dndapp.data.entity.CharacterClass

class CharacterClassConverter {
    @TypeConverter
    fun fromString(value: String): CharacterClass = CharacterClass.valueOf(value)

    @TypeConverter
    fun fromCharacterClass(value: CharacterClass): String = value.name
}