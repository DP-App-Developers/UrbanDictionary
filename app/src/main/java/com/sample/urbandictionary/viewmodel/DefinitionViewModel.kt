package com.sample.urbandictionary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.model.DefinitionRoomDatabase
import com.sample.urbandictionary.repository.DefinitionRepository

open class DefinitionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DefinitionRepository
    private var definitions: LiveData<List<Definition>>? = null
    private var observer = Observer<List<Definition>> { definitions -> data.value = definitions }
    val data: MediatorLiveData<List<Definition>> by lazy {
        MediatorLiveData<List<Definition>>()
    }

    init {
        val definitionsDao = DefinitionRoomDatabase.getDatabase(application, viewModelScope).definitionDao()
        repository = DefinitionRepository(definitionsDao)
    }

    fun loadDefinitionsSortByThumbsUp(word: String) {
        definitions?.let {
            data.removeSource(it)
        }
        definitions = repository.loadDefinitionsSortByThumbsUp(word)
        definitions?.let {
            data.addSource(it, observer)
        }
    }

    fun loadDefinitionsSortByThumbsDown(word: String) {
        definitions?.let {
            data.removeSource(it)
        }
        definitions = repository.loadDefinitionsSortByThumbsDown(word)
        definitions?.let {
            data.addSource(it, observer)
        }
    }

}