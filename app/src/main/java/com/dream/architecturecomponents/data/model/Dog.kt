package com.dream.architecturecomponents.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dog")
data class Dog(

    @PrimaryKey
    override var id: Int = 0,

    var image: String = "image manquante"

): BaseObject