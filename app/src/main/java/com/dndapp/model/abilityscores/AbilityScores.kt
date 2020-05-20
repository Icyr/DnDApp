package com.dndapp.model.abilityscores

import com.dndapp.utils.HasId
import java.io.Serializable

data class AbilityScores (
    var strength: Int = 20,
    var dexteriety: Int = 15,
    var constitution: Int = 3,
    var intelligence: Int = 3,
    var wisdom: Int = 3,
    var charisma: Int = 100,
    override val id: Long = 0

) : HasId<Long>, Serializable





