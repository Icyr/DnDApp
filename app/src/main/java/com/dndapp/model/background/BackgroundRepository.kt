package com.dndapp.model.background

import androidx.lifecycle.Transformations
import com.dndapp.model.background.room.BackgroundDao
import com.dndapp.model.background.room.BackgroundEntity

class BackgroundRepository(backgroundDao: BackgroundDao) {

    val transformation: (entity: BackgroundEntity) -> Background = {
        Background(it.name, it.id)
    }
    val backgrounds = Transformations.map(backgroundDao.getBackgrounds()) {
        it.map(transformation)
    }
}