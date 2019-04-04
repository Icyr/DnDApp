package com.dndapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1)
abstract class DndAppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}