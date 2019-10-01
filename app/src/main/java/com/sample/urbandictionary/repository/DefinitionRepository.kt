package com.sample.urbandictionary.repository

import androidx.lifecycle.LiveData
import com.sample.urbandictionary.api.UrbanDictionaryService
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.model.DefinitionDao
import com.sample.urbandictionary.model.DefinitionList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class DefinitionRepository(private val definitionDao: DefinitionDao) {

    private val urbanDictionaryService by lazy {
        UrbanDictionaryService.create()
    }

    fun loadDefinitionsSortByThumbsUp(word: String): LiveData<List<Definition>> {
        fetchNewDefinitions(word)
        return definitionDao.getDefinitionsSortByThumbsUp(word)
    }

    fun loadDefinitionsSortByThumbsDown(word: String): LiveData<List<Definition>> {
        fetchNewDefinitions(word)
        return definitionDao.getDefinitionsSortByThumbsDown(word)
    }

    private fun fetchNewDefinitions(word: String) {
        val definitionList = urbanDictionaryService.getDefinitions(word)
        definitionList.enqueue(object : Callback<DefinitionList> {
            override fun onResponse(call: Call<DefinitionList>, response: Response<DefinitionList>) {
                if (response.isSuccessful) {
                    val definitions = response.body()?.definitionList
                    CoroutineScope(Dispatchers.IO).launch {
                        definitionDao.updateWord(word, definitions)
                    }
                }
            }

            override fun onFailure(call: Call<DefinitionList>, t: Throwable) {
            }
        })
    }

}