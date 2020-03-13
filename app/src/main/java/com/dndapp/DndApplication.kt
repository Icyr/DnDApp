package com.dndapp

import android.app.Application
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.character.list.CharacterListViewModel
import com.dndapp.model.character.CharacterRepository
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DndApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DndApplication)
            modules(
                module {
                    // repository
                    single { CharacterRepository() }
                    // view model
                    single { NavigationViewModel() }
                    // android view model
                    viewModel { CharacterListViewModel(get(), get()) }
                    viewModel { CharacterCreateViewModel(get(), get()) }
                }
            )
        }
    }
}