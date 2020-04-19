package com.dndapp.character.create.race

import androidx.lifecycle.ViewModel
import com.dndapp.model.race.RaceRepository
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.NavigationViewModel

class CharacterCreateRaceViewModel(
    raceRepository: RaceRepository,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val races = raceRepository.races

    fun onBack() = navigationViewModel.navigate(Back())
}