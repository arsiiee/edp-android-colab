package com.liceo.mysocial.data
 
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
 
// TODO 6a: list Post as the table, set version = 1, exportSchema = false
@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
 
    // TODO 6b: declare the DAO here
    abstract fun postDao(): PostDao
 
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
 
        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mysocial.db",
                ).build().also { INSTANCE = it }
            }
    }
}