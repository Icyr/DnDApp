package com.dndapp.character.list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.landing.signin.SignInState
import com.dndapp.model.character.CharacterRepository
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel

class CharacterListViewModel(
    characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {


    val state = BaseObservableLiveData(CharacterListState())

    val characters = characterRepository.getCharacters()

    fun onCreate() = navigationViewModel.navigate(Destination(R.id.fragment_character_create))


    class CharacterListState : BaseObservable() {

        @get:Bindable
        var loading: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.loading)
            }
    }
}