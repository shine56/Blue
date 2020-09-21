package com.shine56.blue.model.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shine56.blue.base.MyApplication
import com.shine56.blue.model.bean.RequestBean

@Database(entities = [RequestBean::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun requestDao(): RequestDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDataBase(): AppDatabase{
            instance?.let { return it }

            return Room.databaseBuilder(
                MyApplication.context,
                AppDatabase::class.java,
                "Blue")
                //.addMigrations(MIGRATION_1_2)
                .build()
                .apply { instance = this }
        }
    }
}