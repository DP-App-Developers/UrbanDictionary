package com.sample.urbandictionary.repository

import androidx.lifecycle.LiveData
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.model.DefinitionDao

class DefinitionRepository(private val definitionDao: DefinitionDao) {

    suspend fun insert(definition: Definition) {
        definitionDao.insert(definition)
    }

    fun loadDefinitions(word: String): LiveData<List<Definition>> {
        return definitionDao.getDefinitionsSortByThumbsUp(word)
    }
}