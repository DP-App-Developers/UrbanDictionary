package com.sample.urbandictionary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "definition_table")
data class Definition(
        @field:SerializedName("word")
        @ColumnInfo(name = "word") val word: String,
        @field:SerializedName("definition")
        @ColumnInfo(name = "definition") val definition: String,
        @field:SerializedName("thumbs_up")
        @ColumnInfo(name = "thumbs_up") val thumbsUp: Int,
        @field:SerializedName("thumbs_down")
        @ColumnInfo(name = "thumbs_down") val thumbsDown: Int) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0
}