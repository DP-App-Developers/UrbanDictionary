package com.sample.urbandictionary.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.urbandictionary.R
import com.sample.urbandictionary.viewmodel.DefinitionViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var definitionViewModel: DefinitionViewModel
    private lateinit var adapter: DefinitionRecyclerAdapter
    private lateinit var userSearchInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sample.urbandictionary.R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(com.sample.urbandictionary.R.id.recyclerview)
        adapter = DefinitionRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
        definitionViewModel.data.observe(this, Observer { definitions ->
            definitions?.let { adapter.setDefinitions(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.sample.urbandictionary.R.menu.main_activity, menu)
        val searchItem = menu?.findItem(com.sample.urbandictionary.R.id.menu_search) as MenuItem
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                userSearchInput = s.trim().toLowerCase()
                definitionViewModel.loadDefinitionsSortByThumbsUp(userSearchInput)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_sort_by_most_thumbs_up -> {
                definitionViewModel.loadDefinitionsSortByThumbsUp(userSearchInput)
                true
            }
            R.id.menu_sort_by_most_thumbs_down -> {
                definitionViewModel.loadDefinitionsSortByThumbsDown(userSearchInput)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
