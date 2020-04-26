package com.dndapp.model.background

import com.dndapp.model.Repository
import com.dndapp.model.background.room.BackgroundDao
import com.dndapp.model.background.room.BackgroundEntity

class BackgroundRepository(private val backgroundDao: BackgroundDao) : Repository<Background> {

    val transformation: (entity: BackgroundEntity) -> Background = {
        Background(it.name, it.id)
    }

    override fun loadAll(): List<Background> = backgroundDao.getBackgrounds().map(transformation)
}