package com.grepi.ngerisep.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.grepi.ngerisep.entity.MealsMark

@Dao
interface MealsDAO {

    @Query("SELECT * FROM mealsTable")
    fun getAllMeals() : LiveData<List<MealsMark>>

    @Query("SELECT * FROM mealsTable WHERE idMeal IN (:mealsId)")
    fun getMealsById(mealsId : String) : List<MealsMark>

    @Insert
    fun insertAll(vararg mealsMark: MealsMark)

    @Delete
    fun deleteMeals(mealsMark: MealsMark)
}