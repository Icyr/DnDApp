package com.dndapp.model.background.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dndapp.model.BaseDao

@Dao
interface BackgroundDao : BaseDao<BackgroundEntity> {

    @Query("SELECT * FROM background")
    fun getBackgrounds(): LiveData<List<BackgroundEntity>>
}