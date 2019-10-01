package com.sample.urbandictionary.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DefinitionDao {
    @Query("SELECT * FROM definition_table WHERE lower(word) = :word ORDER BY thumbs_up DESC")
    fun getDefinitionsSortByThumbsUp(word: String): LiveData<List<Definition>>

    @Query("SELECT * FROM definition_table ORDER BY definition DESC")
    fun getAll(): LiveData<List<Definition>>

    @Query("SELECT * FROM definition_table WHERE lower(word) = :word ORDER BY thumbs_down DESC")
    fun getDefinitionsSortByThumbsDown(word: String): LiveData<List<Definition>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(definitions: List<Definition>)

    @Query("DELETE FROM definition_table")
    suspend fun deleteAll()

    @Query("DELETE FROM definition_table WHERE lower(word) = :word")
    suspend fun deleteWord(word: String)

    @Transaction
    suspend fun updateWord(word: String, definitions: List<Definition>?) {
        deleteWord(word)
        definitions?.let {
            insert(it)
        }
    }

}