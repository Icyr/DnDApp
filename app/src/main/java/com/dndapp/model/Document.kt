package com.dndapp.model

import com.dndapp.utils.HasId
import java.io.Serializable

data class Document<T>(override val id: String, val data: T?) : HasId<String>, Serializable