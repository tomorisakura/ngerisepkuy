package com.grepi.ngerisep.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grepi.ngerisep.entity.MealsMark

@Database(entities = [MealsMark::class], version = 1, exportSchema = false)
abstract class MealsDatabase : RoomDatabase() {
    abstract fun mealsMarkDAO() : MealsDAO

    companion object {
        private var INSTANCE : MealsDatabase? = null

        fun getInstance(context: Context) : MealsDatabase {
            if (INSTANCE == null) {
                synchronized(MealsDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, MealsDatabase::class.java, "mealsTable")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as MealsDatabase
        }
    }
}