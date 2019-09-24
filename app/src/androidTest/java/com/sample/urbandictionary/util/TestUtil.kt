package com.sample.urbandictionary.util

import com.sample.urbandictionary.model.Definition

object TestUtil {

    fun createDefinition(word: String, definition: String, thumbsUp: Int, thumbsDown: Int) = Definition(
        word = word,
        definition = definition,
        thumbsUp = thumbsUp,
        thumbsDown = thumbsDown
    )
}