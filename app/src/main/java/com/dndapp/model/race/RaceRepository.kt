package com.dndapp.model.race

import com.dndapp.model.Repository
import com.dndapp.model.race.room.RaceDao
import com.dndapp.model.race.room.RaceEntity

class RaceRepository(private val raceDao: RaceDao) : Repository<Race> {

    val transformation: (input: RaceEntity) -> Race = {
        Race(it.name, it.id)
    }

    override fun loadAll(): List<Race> = raceDao.getRaces().map(transformation)
}