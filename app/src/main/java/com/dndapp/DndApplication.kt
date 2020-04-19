package com.dndapp

import android.app.Application
import com.dndapp.model.race.room.RaceDao
import com.dndapp.model.race.room.RaceEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DndApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DndApplication)
            modules(mainModule, roomDBModule)
        }
        // TODO extract to initializer
        val raceDao = get<RaceDao>()
        GlobalScope.launch {
            raceDao.insertIgnoreConflict(RaceEntity("Human", 1))
            raceDao.insertIgnoreConflict(RaceEntity("Orc", 2))
            raceDao.insertIgnoreConflict(RaceEntity("Elf", 3))
        }
    }
}