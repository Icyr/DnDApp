package com.dndapp.model.race.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dndapp.model.BaseDao

@Dao
interface RaceDao : BaseDao<RaceEntity> {

    @Query("SELECT * FROM race")
    fun getRaces(): LiveData<List<RaceEntity>>
}