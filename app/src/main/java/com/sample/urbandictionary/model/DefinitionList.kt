package com.sample.urbandictionary.model

import com.google.gson.annotations.SerializedName

data class DefinitionList(
    @field:SerializedName("list")
    val definitionList: List<Definition>
)