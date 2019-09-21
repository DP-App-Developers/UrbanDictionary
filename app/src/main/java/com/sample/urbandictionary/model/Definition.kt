package com.sample.urbandictionary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definition_table")
data class Definition(@ColumnInfo(name = "word") val word: String,
                      @ColumnInfo(name = "definition") val definition: String,
                      @ColumnInfo(name = "thumbsUp") val thumbsUp: Int,
                      @ColumnInfo(name = "thumbsDown") val thumbsDown: Int) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0
}