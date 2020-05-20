package com.dndapp.model.abilityscores.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dndapp.utils.HasId
import java.io.Serializable

@Entity(tableName = "ability_scores")
data class AbilityScoresEntity(
    var strength: Int,
    var dexteriety: Int,
    var constitution: Int,
    var intelligence: Int,
    var wisdom: Int,
    var charisma: Int,
    @PrimaryKey(autoGenerate = true) override val id: Long = 0
) : HasId<Long>, Serializable