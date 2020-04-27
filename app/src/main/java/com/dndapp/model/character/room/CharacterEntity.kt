package com.dndapp.model.character.room

import androidx.room.*
import com.dndapp.model.background.room.BackgroundEntity
import com.dndapp.model.characterClass.room.CharacterClassEntity
import com.dndapp.model.race.room.RaceEntity
import com.dndapp.utils.HasId
import java.io.Serializable

@Entity(
    tableName = "character",
    foreignKeys = [
        ForeignKey(entity = RaceEntity::class, parentColumns = ["id"], childColumns = ["race_id"]),
        ForeignKey(entity = BackgroundEntity::class, parentColumns = ["id"], childColumns = ["background_id"]),
        ForeignKey(entity = CharacterClassEntity::class, parentColumns = ["id"], childColumns = ["character_class"])
    ],
    indices = [Index("race_id"), Index("background_id"), Index("character_class")]
)
data class CharacterEntity(
    val name: String,
    @ColumnInfo(name = "race_id") val raceId: Long,
    @ColumnInfo(name = "background_id") val backgroundId: Long,
    @ColumnInfo(name = "character_class") val characterClass: Long,
    @PrimaryKey(autoGenerate = true) override val id: Long = 0
) : HasId<Long>, Serializable