package com.sample.urbandictionary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Definition::class], version = 1)
abstract class DefinitionRoomDatabase : RoomDatabase() {

    abstract fun definitionDao(): DefinitionDao

    companion object {
        @Volatile
        private var INSTANCE: DefinitionRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): DefinitionRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DefinitionRoomDatabase::class.java,
                    "definition_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}