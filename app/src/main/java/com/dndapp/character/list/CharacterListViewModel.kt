package com.dndapp.character.list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.model.character.Character
import com.dndapp.model.character.CharacterRepository
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val characters = MutableLiveData<List<Character>>()

    val state = BaseObservableLiveData(CharacterListView())

    fun onCreate() = navigationViewModel.navigate(Destination(R.id.character_create_graph))


    init {
        loadCharacters()
    }

    fun loadCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            state.value!!.loading = true
            delay(5000)
            characters.postValue(
                characterRepository.loadCharacters()
            )
            state.value!!.loading = false
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
