package com.dndapp.model.background.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dndapp.utils.HasId
import java.io.Serializable

@Entity(tableName = "background")
data class BackgroundEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) override val id: Long = 0
) : HasId<Long>, Serializable