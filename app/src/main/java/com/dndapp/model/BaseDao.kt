package com.dndapp.model

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert
    fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnoreConflict(entity: T)
}