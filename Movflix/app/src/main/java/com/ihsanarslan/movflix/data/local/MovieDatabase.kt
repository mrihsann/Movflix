package com.ihsanarslan.movflix.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ihsanarslan.movflix.data.local.dao.MovieDAO
import com.ihsanarslan.movflix.domain.model.MovieDetail

@Database(entities = [MovieDetail::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object {
        private var INSTANCE: MovieDatabase? = null


        fun getInstance(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java, "my_favorites"
                    ).build()
                }
            }
            return INSTANCE!!
        }

    }
}
