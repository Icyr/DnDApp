package com.dndapp.model.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class CharacterRepository {

    private var db = FirebaseFirestore.getInstance()
    private var auth = FirebaseAuth.getInstance()

    private val _characters = MutableLiveData(

     //   getCharacterFromFireStore()

      listOf(
            Character("1", "Character #1"),
            Character("2", "Character #2"),
            Character("3", "Character #3"),
            Character("4", "Character #4")
        )
    )
    val characters: LiveData<List<Character>>
        get() = _characters

    fun addCharacter(name: String) {
        val lastId = _characters.value?.last()?.id?.toInt() ?: 0
        _characters.value = _characters.value.orEmpty().toMutableList().apply {
            add(Character("${lastId + 1}", name))
        }.toList()
    }

    fun addCharacterToFireStore(name: String) {
        val characterImpl: MutableMap<String, Any> = HashMap()
        val lastId = _characters.value?.last()?.id?.toInt() ?: 0

        characterImpl["userId"] = auth.currentUser.toString()
        characterImpl["id"] = "${lastId + 1}"
        characterImpl["name"] = name

        db.collection("characters")
            .add(characterImpl)
            .addOnSuccessListener { _ ->
            }
            .addOnFailureListener { e -> }
    }


    fun getCharacterFromFireStore() {
        db.collection("characters")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("CHAR", document.getId() + " => " + document.getData());
                    }
                } else {
                 //   Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }
}
