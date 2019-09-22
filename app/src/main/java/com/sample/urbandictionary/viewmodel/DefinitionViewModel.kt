package com.sample.urbandictionary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.model.DefinitionRoomDatabase
import com.sample.urbandictionary.repository.DefinitionRepository
import kotlinx.coroutines.launch

class DefinitionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DefinitionRepository

    init {
        val definitionsDao = DefinitionRoomDatabase.getDatabase(application, viewModelScope).definitionDao()
        repository = DefinitionRepository(definitionsDao)
    }

    fun insert(definition: Definition) = viewModelScope.launch {
        repository.insert(definition)
    }

    fun loadDefinitions(word: String): LiveData<List<Definition>> {
        return repository.loadDefinitions(word)
    }

}