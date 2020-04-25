package com.dndapp.character.create

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.model.background.Background
import com.dndapp.model.character.Character
import com.dndapp.model.character.CharacterRepository
import com.dndapp.model.race.Race
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.PopUpTo
import com.dndapp.viewmodel.SoftKeyboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterCreateViewModel(
    private val characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel,
    private val softKeyboardViewModel: SoftKeyboardViewModel
) : ViewModel() {

    val state = BaseObservableLiveData(CharacterCreateState())

    fun onSubmit() {
        state.value?.let { state ->
            softKeyboardViewModel.hide()
            val race = state.race
            val background = state.background
            if (race != null && background != null) {
                viewModelScope.launch(Dispatchers.IO) {
                    characterRepository.add(Character(state.name, race, background))
                }
            }
        }

        navigationViewModel.navigate(
            Destination(
                R.id.fragment_character_list,
                popUpTo = PopUpTo(R.id.fragment_character_list, true)
            )
        )
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
    var race: Race? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.race)
        }

    @get:Bindable
    var background: Background? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.background)
        }
}