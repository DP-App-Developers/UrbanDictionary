package com.sample.urbandictionary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
                    .addCallback(DefinitionDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DefinitionDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.definitionDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(definitionDao: DefinitionDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            definitionDao.deleteAll()

            var definition = Definition("Hello", "greeting people greeting people greeting people greeting people", 8, 5)
            definitionDao.insert(definition)
            definition = Definition("World1", "the earth the earth the earth the earth the earth the earth the earth", 7, 3)
            definitionDao.insert(definition)
            definition = Definition("World2", "the earth the earth the earth the earth the earth the earth the earth", 7, 3)
            definitionDao.insert(definition)
            definition = Definition("World2", "the earth the earth the earth the earth the earth the earth the earth yo", 7, 3)
            definitionDao.insert(definition)
        }
    }
}