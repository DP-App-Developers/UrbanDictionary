package com.sample.urbandictionary.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.urbandictionary.R
import com.sample.urbandictionary.viewmodel.DefinitionViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var definitionViewModel: DefinitionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = DefinitionRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)

        // The onChanged() method fires when the observed data changes and the activity is in the foreground
        definitionViewModel.definitions.observe(this, Observer { definitions ->
            // Update the cached copy of the words in the adapter.
            definitions?.let { adapter.setDefinitions(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_search -> {
                true
            }
            R.id.menu_sort_by -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
