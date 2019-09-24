package com.sample.urbandictionary.model

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class ModelTest {
    @Test
    fun testDefinition() {
        val definition = Definition("wat", "response to something that makes no sense", 99, 3)
        Assert.assertThat(definition.word, CoreMatchers.`is`("wat"))
        Assert.assertThat(definition.definition, CoreMatchers.`is`("response to something that makes no sense"))
        Assert.assertThat(definition.thumbsUp, CoreMatchers.`is`(99))
        Assert.assertThat(definition.thumbsDown, CoreMatchers.`is`(3))
    }
}