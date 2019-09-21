package com.sample.urbandictionary.repository

import androidx.lifecycle.LiveData
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.model.DefinitionDao

class DefinitionRepository(private val definitionDao: DefinitionDao) {

    val definitions: LiveData<List<Definition>> = definitionDao.getDefinitions()

    suspend fun insert(definition: Definition) {
        definitionDao.insert(definition)
    }
}