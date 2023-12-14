package com.example.minhasreceitas.data

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


//definindo a construção e instância do nosso db
val Context.db : AppDatabase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-recipe"
    ).addMigrations(
        MIGRATE_01_02
    ).build()

internal val MIGRATE_01_02 = migrate(1..2) {

    //inportante ter default zero para que os resultado antigos sejam atribuidos ;
    //not null pq a informação do db é obrigatória
    """ALTER TABLE `recipe` ADD COLUMN `prepareTime´ TEXT DEFAULT 0 NOT NULL;"""
}

private fun migrate(version: IntRange, sql: () -> String) =
    object : Migration (version.first, version.last){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(sql())
        }
    }