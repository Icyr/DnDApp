package com.dndapp.model.character.room

import androidx.room.*
import com.dndapp.model.race.room.RaceEntity
import com.dndapp.utils.HasId
import java.io.Serializable

@Entity(
    tableName = "character",
    foreignKeys = [ForeignKey(entity = RaceEntity::class, parentColumns = ["id"], childColumns = ["race_id"])],
    indices = [Index("race_id")]
)
data class CharacterEntity(
    val name: String,
    @ColumnInfo(name = "race_id") val raceId: Long,
    @PrimaryKey(autoGenerate = true) override val id: Long = 0
) : HasId<Long>, Serializable