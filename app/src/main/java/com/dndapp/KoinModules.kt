package com.dndapp

import androidx.room.Room
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.character.list.CharacterListViewModel
import com.dndapp.landing.signin.SignInViewModel
import com.dndapp.landing.signup.SignUpViewModel
import com.dndapp.model.AppDatabase
import com.dndapp.model.DATABASE_NAME
import com.dndapp.model.character.CharacterRepository
import com.dndapp.model.character.FirestoreCharacterRepository
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.SoftKeyboardViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    viewModel { SignInViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    // Firebase
    single { FirebaseAuth.getInstance() }
}

val fireStoreDBModule = module {
    single { FirebaseFirestore.getInstance() }
    single<CharacterRepository> { FirestoreCharacterRepository(get(), get()) }
}

val roomDBModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, DATABASE_NAME).build() }
    single { get<AppDatabase>().characterDao() }
}