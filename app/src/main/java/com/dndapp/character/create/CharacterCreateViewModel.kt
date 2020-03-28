package com.dndapp.character.create

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.model.character.Character
import com.dndapp.model.character.CharacterRepository
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterCreateViewModel(
    private val characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel,
    private val softKeyboardViewModel: SoftKeyboardViewModel
) : ViewModel() {

    val state = BaseObservableLiveData(CharacterCreateState())

    fun onNext() {
       navigationViewModel.navigate(Destination(R.id.fragment_character_race))
    }

    fun onSubmit() {
        state.value?.let { it ->
            softKeyboardViewModel.hide()
            viewModelScope.launch(Dispatchers.IO) {
                characterRepository.addCharacter(Character(it.name, it.race))
            }
        }

        navigationViewModel.navigate(Destination(R.id.fragment_character_list))
    }

}

class CharacterCreateState : BaseObservable() {
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var race: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.race)
        }

    val canNext: Boolean
        get() = name.isNotBlank()

    val canSubmit: Boolean
        get() = race.isNotBlank()
}