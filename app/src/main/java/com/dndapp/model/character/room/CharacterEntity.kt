package com.dndapp.model.character.room

import androidx.room.*
import com.dndapp.model.abilityscores.AbilityScores
import com.dndapp.model.abilityscores.room.AbilityScoresEntity
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
        ForeignKey(entity = CharacterClassEntity::class, parentColumns = ["id"], childColumns = ["character_class_id"]),
        ForeignKey(entity = AbilityScoresEntity::class, parentColumns = ["id"], childColumns = ["ability_scores_id"])

],
    indices = [Index("race_id"), Index("background_id"), Index("character_class_id"), Index("ability_scores_id")]
)
data class CharacterEntity(
    val name: String,
    @ColumnInfo(name = "race_id") val raceId: Long,
    @ColumnInfo(name = "background_id") val backgroundId: Long,
    @ColumnInfo(name = "character_class_id") val characterClassId: Long,
    @Embedded(prefix = "ability_scores_id_")   val abilityScores: AbilityScores,
    @PrimaryKey(autoGenerate = true) override val id: Long = 0
) : HasId<Long>, Serializable