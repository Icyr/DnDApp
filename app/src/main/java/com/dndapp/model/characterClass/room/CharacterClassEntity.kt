package com.dndapp.model.characterClass.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dndapp.utils.HasId
import java.io.Serializable

@Entity(tableName = "characterClass")
data class CharacterClassEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) override val id: Long = 0
) : HasId<Long>, Serializable