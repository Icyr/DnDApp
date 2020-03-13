package com.dndapp.model.character

import com.dndapp.utils.HasId

data class Character(override val id: String, val name: String) : HasId<String>