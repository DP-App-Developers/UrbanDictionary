package com.sample.urbandictionary.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DefinitionDao {
    @Query("SELECT * from definition_table ORDER BY definition ASC")
    fun getDefinitions(): LiveData<List<Definition>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(definition: Definition)

    @Query("DELETE FROM definition_table")
    suspend fun deleteAll()
}