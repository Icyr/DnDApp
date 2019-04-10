package com.dndapp.data

import androidx.room.Delete
import androidx.room.Insert

interface BaseDao<T> {
    @Insert
    fun insert(vararg entity: T)

    @Delete
    fun delete(vararg entity: T)
}