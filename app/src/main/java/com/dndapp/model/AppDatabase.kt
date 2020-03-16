package com.dndapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dndapp.model.character.Character
import com.dndapp.model.character.CharacterRepository

const val DATABASE_NAME = "dnd_app_db"

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterRepository
}