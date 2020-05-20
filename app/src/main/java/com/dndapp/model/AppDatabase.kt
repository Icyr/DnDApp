package com.dndapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dndapp.model.abilityscores.room.AbilityScoresEntity
import com.dndapp.model.background.room.BackgroundDao
import com.dndapp.model.background.room.BackgroundEntity
import com.dndapp.model.character.room.CharacterDao
import com.dndapp.model.character.room.CharacterEntity
import com.dndapp.model.characterClass.room.CharacterClassDao
import com.dndapp.model.characterClass.room.CharacterClassEntity
import com.dndapp.model.race.room.RaceDao
import com.dndapp.model.race.room.RaceEntity

const val DATABASE_NAME = "dnd_app_db"

@Database(entities = [CharacterEntity::class, RaceEntity::class, BackgroundEntity::class, CharacterClassEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun raceDao(): RaceDao

    abstract fun backgroundDao(): BackgroundDao

    abstract fun characterClassDao(): CharacterClassDao

}