package com.dndapp.data

import androidx.room.Insert

interface BaseDao<T> {
    @Insert
    fun insert(vararg entity: T)
}