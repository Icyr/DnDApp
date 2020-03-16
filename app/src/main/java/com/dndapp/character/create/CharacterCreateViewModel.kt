package com.dndapp.character.create

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dndapp.BR
import com.dndapp.model.character.Character
import com.dndapp.model.character.CharacterRepository
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.NavigationViewModel
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
        state.value?.name?.let {
            softKeyboardViewModel.hide()
            viewModelScope.launch(Dispatchers.IO) {
                characterRepository.addCharacter(Character(it))
            }
        }
        navigationViewModel.navigate(Back())
    }
}

class CharacterCreateState : BaseObservable() {
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    val canSubmit: Boolean
        get() = name.isNotBlank()
}