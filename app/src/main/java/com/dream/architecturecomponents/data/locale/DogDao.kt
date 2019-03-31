package com.dream.architecturecomponents.data.locale

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dream.architecturecomponents.data.model.Dog

@Dao
interface DogDao {

    @Query("SELECT * FROM dog WHERE id = :id")
    fun getById(id: Int): LiveData<Dog>

    @Query("SELECT * FROM dog")
    fun getAllLive(): LiveData<List<Dog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dog: Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Dog>)

    @Delete
    fun delete(dog: Dog)

}