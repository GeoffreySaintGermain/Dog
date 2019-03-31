package com.dream.architecturecomponents.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dream.architecturecomponents.data.model.Dog

@Database(entities = [Dog::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {
        const val DATABASE_NAME = "DogDatabase"
    }
}