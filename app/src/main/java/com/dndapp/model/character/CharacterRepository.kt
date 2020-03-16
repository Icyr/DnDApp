package com.dndapp.model.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dndapp.model.Document
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

const val USERS_COLLECTION_NAME = "users"
const val CHARACTER_COLLECTION_NAME = "characters"

class CharacterRepository(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    private val _characters = MutableLiveData<List<Document<Character>>>()
    val characters: LiveData<List<Document<Character>>>
        get() = _characters

    private val userCharacters: CollectionReference?
        get() = auth.uid?.run {
            db.collection(USERS_COLLECTION_NAME)
                .document(this)
                .collection(CHARACTER_COLLECTION_NAME)
        }

    init {
        userCharacters?.addSnapshotListener { snapshot, _ ->
            _characters.postValue(snapshot?.documents?.map {
                Document(it.id, it.data?.run { Character(get("name") as? String ?: "") })
            }.orEmpty())
        }
    }

    fun addCharacter(name: String) = userCharacters?.add(Character(name))
}
