package com.example.minhasreceitas.data

import android.content.Context
import androidx.room.Room


//definindo a construção e instância do nosso db
val Context.db : AppDatabase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-recipe"
    ).build()