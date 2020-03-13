package com.dndapp.character.create

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.dndapp.BR
import com.dndapp.model.character.CharacterRepository
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.NavigationViewModel

class CharacterCreateViewModel(
    private val characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val state = BaseObservableLiveData(CharacterCreateState())

    fun onSubmit() {
        state.value?.name?.let {
            characterRepository.addCharacter(it)
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