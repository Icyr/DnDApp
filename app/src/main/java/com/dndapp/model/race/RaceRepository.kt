package com.dndapp.model.race

import androidx.lifecycle.Transformations
import com.dndapp.model.race.room.RaceDao
import com.dndapp.model.race.room.RaceEntity

class RaceRepository(raceDao: RaceDao) {
    val transformation: (input: RaceEntity) -> Race = {
        Race(it.name, it.id)
    }
    val races = Transformations.map(raceDao.getRaces()) {
        it.map(transformation)
    }
}