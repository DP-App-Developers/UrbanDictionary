package com.sample.urbandictionary.ui

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.urbandictionary.R
import com.sample.urbandictionary.model.Definition
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

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    private lateinit var viewModel: DefinitionViewModel
    private val results = MutableLiveData<List<Definition>>()
    private lateinit var mockBindingAdapter: DefinitionRecyclerAdapter

    @Before
    fun init() {
        viewModel = Mockito.mock(DefinitionViewModel::class.java)
        Mockito.`when`(viewModel.loadDefinitionsSortByThumbsUp("wat")).thenReturn(results)
        mockBindingAdapter = Mockito.mock(DefinitionRecyclerAdapter::class.java)
    }

    @Test
    fun loadResults() {
        val definition = TestUtil.createDefinition("wat", "response to something that makes no sense", 99, 5)
        results.postValue((arrayListOf(definition)))
        Espresso.onView(listMatcher().atPosition(0)).check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("wat"))))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.recyclerview)
    }
}