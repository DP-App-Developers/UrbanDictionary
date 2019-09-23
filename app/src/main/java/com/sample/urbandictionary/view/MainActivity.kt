package com.sample.urbandictionary.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = DefinitionRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity, menu)
        val searchItem = menu?.findItem(R.id.menu_search) as MenuItem
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                userSearchInput = s.trim().toLowerCase()
                definitionViewModel.loadDefinitionsSortByThumbsUp(userSearchInput).observe(this@MainActivity, Observer { definitions ->
                    definitions?.let { adapter.setDefinitions(it) }
                })
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
                definitionViewModel.loadDefinitionsSortByThumbsUp(userSearchInput).observe(this@MainActivity, Observer { definitions ->
                    definitions?.let { adapter.setDefinitions(it) }
                })
                true
            }
            R.id.menu_sort_by_most_thumbs_down -> {
                definitionViewModel.loadDefinitionsSortByThumbsDown(userSearchInput).observe(this@MainActivity, Observer { definitions ->
                    definitions?.let { adapter.setDefinitions(it) }
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
