package com.sample.urbandictionary.ui

import androidx.lifecycle.MediatorLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.urbandictionary.R
import com.sample.urbandictionary.model.Definition
import com.sample.urbandictionary.repository.DefinitionRepository
import com.sample.urbandictionary.util.RecyclerViewMatcher
import com.sample.urbandictionary.util.TestUtil
import com.sample.urbandictionary.view.DefinitionRecyclerAdapter
import com.sample.urbandictionary.view.MainActivity
import com.sample.urbandictionary.viewmodel.DefinitionViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    private lateinit var viewModel: DefinitionViewModel
    private lateinit var repository: DefinitionRepository
    private val results = MediatorLiveData<List<Definition>>()
    private lateinit var mockBindingAdapter: DefinitionRecyclerAdapter

    @Before
    fun init() {
        viewModel = mock(DefinitionViewModel::class.java)
        repository = mock(DefinitionRepository::class.java)
        `when`(repository.loadDefinitionsSortByThumbsUp("wat")).thenReturn(results)
        mockBindingAdapter = Mockito.mock(DefinitionRecyclerAdapter::class.java)
    }

    @Test
    fun loadResults() {
        val definition = TestUtil.createDefinition("wat", "response to something that makes no sense", 99, 5)
        results.postValue((arrayListOf(definition)))
        viewModel.loadDefinitionsSortByThumbsUp("wat")
        Espresso.onView(listMatcher().atPosition(0)).check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("wat"))))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.recyclerview)
    }
}