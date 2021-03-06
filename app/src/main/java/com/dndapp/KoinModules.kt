package com.dndapp

import androidx.room.Room
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.character.create.background.BackgroundListViewModel
import com.dndapp.character.create.background.CharacterCreateBackgroundViewModel
import com.dndapp.character.create.characterClass.CharacterClassListViewModel
import com.dndapp.character.create.characterClass.CharacterCreateClassViewModel
import com.dndapp.character.create.name.CharacterCreateNameViewModel
import com.dndapp.character.create.race.CharacterCreateRaceViewModel
import com.dndapp.character.create.race.RaceListViewModel
import com.dndapp.character.list.CharacterListViewModel
import com.dndapp.landing.signin.SignInViewModel
import com.dndapp.landing.signup.SignUpViewModel
import com.dndapp.model.AppDatabase
import com.dndapp.model.DATABASE_NAME
import com.dndapp.model.background.BackgroundRepository
import com.dndapp.model.character.CharacterRepository
import com.dndapp.model.characterClass.CharacterClassRepository
import com.dndapp.model.race.RaceRepository
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.SoftKeyboardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    // view model
    single { NavigationViewModel() }
    single { SoftKeyboardViewModel() }
    // android view model
    viewModel { CharacterListViewModel(get(), get()) }
    viewModel { CharacterCreateViewModel(get(), get(), get()) }
    viewModel { CharacterCreateNameViewModel(get()) }
    viewModel { CharacterCreateRaceViewModel(get()) }
    viewModel { RaceListViewModel(get()) }
    viewModel { CharacterCreateBackgroundViewModel(get()) }
    viewModel { BackgroundListViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel() }
    viewModel { CharacterCreateClassViewModel(get()) }
    viewModel { CharacterClassListViewModel(get()) }
}

val roomDBModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, DATABASE_NAME).build() }
    single { get<AppDatabase>().characterDao() }
    single { get<AppDatabase>().raceDao() }
    single { get<AppDatabase>().characterClassDao() }
    single { get<AppDatabase>().backgroundDao() }
    single { CharacterRepository(get(), get(), get(), get()) }
    single { RaceRepository(get()) }
    single { BackgroundRepository(get()) }
    single { CharacterClassRepository(get()) }
}