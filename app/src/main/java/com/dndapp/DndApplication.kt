package com.dndapp

import android.app.Application
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.character.list.CharacterListViewModel
import com.dndapp.landing.signin.SignInViewModel
import com.dndapp.landing.signup.SignUpViewModel
import com.dndapp.model.character.CharacterRepository
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.SoftKeyboardViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DndApplication : Application() {

    private val mainModule = module {
        // repository
        single { CharacterRepository(get(), get()) }
        // view model
        single { NavigationViewModel() }
        single { SoftKeyboardViewModel() }
        // android view model
        viewModel { CharacterListViewModel(get(), get()) }
        viewModel { CharacterCreateViewModel(get(), get(), get()) }
        viewModel { SignInViewModel(get(), get(), get()) }
        viewModel { SignUpViewModel(get(), get(), get()) }
        // Firebase
        single { FirebaseAuth.getInstance() }
    }

    private val fireStoreDBModule = module {
        single { FirebaseFirestore.getInstance() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DndApplication)
            modules(mainModule, fireStoreDBModule)
        }
    }
}