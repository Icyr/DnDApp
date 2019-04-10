package com.dndapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dndapp.data.entity.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(CharacterClassConverter::class)
abstract class DndAppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}