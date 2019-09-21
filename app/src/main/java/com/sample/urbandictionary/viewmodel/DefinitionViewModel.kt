package com.sample.urbandictionary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.model.DefinitionRoomDatabase
import com.sample.urbandictionary.repository.DefinitionRepository

class DefinitionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DefinitionRepository

    val definitions: LiveData<List<Definition>>

    init {
        val definitionsDao = DefinitionRoomDatabase.getDatabase(application, viewModelScope).definitionDao()
        repository = DefinitionRepository(definitionsDao)
        definitions = repository.definitions
    }
}