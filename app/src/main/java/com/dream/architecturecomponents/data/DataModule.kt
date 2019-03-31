package com.dream.architecturecomponents.data

import androidx.room.Room
import com.dream.architecturecomponents.data.locale.DogDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataModule = module {

    single { Room.databaseBuilder(androidApplication(), DogDatabase::class.java, DogDatabase.DATABASE_NAME).build() }

    single { get<DogDatabase>().DogDao() }

    single { DogRepository() }

}