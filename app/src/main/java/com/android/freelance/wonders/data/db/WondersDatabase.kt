package com.android.freelance.wonders.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.freelance.wonders.data.db.entity.Wonders

@Database(entities = [Wonders::class], version = 1, exportSchema = false)
abstract class WondersDatabase : RoomDatabase() {
    abstract fun wondersDao(): WondersDao

    companion object {
        @Volatile
        private var instance: WondersDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WondersDatabase::class.java,
            "Wonders.db"
        ).build()
       /* ).allowMainThreadQueries().build()*/
    }
}
