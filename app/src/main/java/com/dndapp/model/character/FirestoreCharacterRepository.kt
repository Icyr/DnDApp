package com.dndapp.model.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dndapp.model.CHARACTER_COLLECTION_NAME
import com.dndapp.model.USERS_COLLECTION_NAME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class FirestoreCharacterRepository(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CharacterRepository {

    private val characters = MutableLiveData<List<Character>>()

    private val userCharacters: CollectionReference?
        get() = auth.uid?.run {
            db.collection(USERS_COLLECTION_NAME)
                .document(this)
                .collection(CHARACTER_COLLECTION_NAME)
        }

    init {
        userCharacters?.addSnapshotListener { snapshot, _ ->
            characters.postValue(snapshot?.documents?.map {
                Character(
                    it.data?.run
                    { get("name") as? String } ?: "",
                    it.data?.run
                    { get("race") as? String } ?: ""
                    , it.id
                )
            }.orEmpty())
        }
    }

    override fun getCharacters(): LiveData<List<Character>> = characters

    override fun addCharacter(character: Character) {
        userCharacters?.add(hashMapOf("name" to character.name, "race" to character.race))
    }

    override fun updateCharacter(character: Character) {
        TODO("Not yet implemented")
    }
}