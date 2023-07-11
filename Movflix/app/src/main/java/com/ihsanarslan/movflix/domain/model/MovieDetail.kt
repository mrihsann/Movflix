package com.ihsanarslan.movflix.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class MovieDetail(
    @ColumnInfo(name = "actors")
    val Actors: String,
    @ColumnInfo(name = "title")
    val Title: String,
    @ColumnInfo(name = "poster")
    val Poster: String,
    @ColumnInfo(name = "imdbRating")
    val imdbRating: String,
    @ColumnInfo(name = "genre")
    val Genre: String,
    @ColumnInfo(name = "released")
    val Released: String,
    @ColumnInfo(name = "imdbVotes")
    val imdbVotes: String,
    @ColumnInfo(name = "runtime")
    val Runtime: String,
    @ColumnInfo(name = "plot")
    val Plot: String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}