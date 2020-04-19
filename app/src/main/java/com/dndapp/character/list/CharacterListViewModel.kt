package com.dndapp.character.list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.landing.signup.SignUpState
import com.dndapp.model.character.Character
import com.dndapp.model.character.CharacterRepository
import com.dndapp.model.race.Race
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class CharacterListViewModel(
    characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val characters = characterRepository.characters
  //  val characters = MutableLiveData<List<Character>>()

    val state = BaseObservableLiveData(CharacterListView())

    fun onCreate() = navigationViewModel.navigate(Destination(R.id.character_create_graph))

    fun test(){
        viewModelScope.launch {
            delay(7000)
            val race = Race("Orc", 1)
            val characters = MutableLiveData<List<Character>>()  //comment to test
            characters.postValue(listOf(
                Character("Vad 1", race, 1L),
                Character("Vad 2", race, 2L),
                Character("Vad 3", race, 3L),
                Character("Vad 4", race, 4L),
                Character("Vad 5", race, 5L),
                Character("Vad 6", race, 6L),
                Character("Vad 7", race, 7L)
            ))
        }
    }

    class CharacterListView : BaseObservable() {

        @get:Bindable
        var loading: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.loading)
            }
    }
}