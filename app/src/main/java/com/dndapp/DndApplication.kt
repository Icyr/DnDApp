package com.dndapp

import android.app.Application
import androidx.room.Room
import com.dndapp.data.DndAppDatabase
import com.dndapp.viewmodel.CharacterListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DndApplication : Application() {

    private val characterModule = module {
        single {
            Room.databaseBuilder(applicationContext, DndAppDatabase::class.java, "dnd_app_db")
                .build()
        }
        single {
            get<DndAppDatabase>().characterDao()
        }
        viewModel { CharacterListViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@DndApplication)
            modules(characterModule)
        }
    }
}