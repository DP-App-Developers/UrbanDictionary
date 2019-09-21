package com.sample.urbandictionary.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.urbandictionary.R
import com.sample.urbandictionary.model.Definition
import java.util.Collections.emptyList

class DefinitionRecyclerAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<DefinitionRecyclerAdapter.DefinitionViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var definitions = emptyList<Definition>()

    inner class DefinitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.word)
        val definitionItemView: TextView = itemView.findViewById(R.id.definition)
        val thumbsUpCountItemView: TextView = itemView.findViewById(R.id.thumbsUpCount)
        val thumbsDownCountItemView: TextView = itemView.findViewById(R.id.thumbsDownCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return DefinitionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        val current = definitions[position]
        holder.wordItemView.text = current.word
        holder.definitionItemView.text = current.definition
        holder.thumbsUpCountItemView.text = current.thumbsUp.toString()
        holder.thumbsDownCountItemView.text = current.thumbsDown.toString()
    }

    internal fun setDefinitions(definitions: List<Definition>) {
        this.definitions = definitions
        notifyDataSetChanged()
    }

    override fun getItemCount() = definitions.size
}