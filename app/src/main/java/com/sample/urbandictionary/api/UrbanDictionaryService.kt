package com.sample.urbandictionary.api

import com.sample.urbandictionary.model.DefinitionList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UrbanDictionaryService {

    @Headers("x-rapidapi-key:d36b2ab858msh7daf533bacdef2ep195f9ejsna7fc444ddacb", "x-rapidapi-host:mashape-community-urban-dictionary.p.rapidapi.com")
    @GET("/define")
    fun getDefinitions(@Query("term") word: String): Call<DefinitionList>

    companion object {
        fun create(): UrbanDictionaryService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
                .build()

            return retrofit.create(UrbanDictionaryService::class.java)
        }
    }

}