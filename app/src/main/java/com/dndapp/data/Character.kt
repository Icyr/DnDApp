package com.dndapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(@PrimaryKey(autoGenerate = true) val id: Long,
                     val name: String)