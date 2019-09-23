package com.sample.urbandictionary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.model.DefinitionRoomDatabase
import com.sample.urbandictionary.repository.DefinitionRepository

class DefinitionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DefinitionRepository
    private var data: LiveData<List<Definition>> = MediatorLiveData<List<Definition>>()

    init {
        val definitionsDao = DefinitionRoomDatabase.getDatabase(application, viewModelScope).definitionDao()
        repository = DefinitionRepository(definitionsDao)
    }

    fun loadDefinitionsSortByThumbsUp(word: String): LiveData<List<Definition>> {
        data = repository.loadDefinitionsSortByThumbsUp(word)
        return data
    }

    fun loadDefinitionsSortByThumbsDown(word: String): LiveData<List<Definition>> {
        data = repository.loadDefinitionsSortByThumbsDown(word)
        return data
    }

}