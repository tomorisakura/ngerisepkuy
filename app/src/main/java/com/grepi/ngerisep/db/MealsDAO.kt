package com.grepi.ngerisep.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.grepi.ngerisep.entity.MealsMark

@Dao
interface MealsDAO {

    @Query("SELECT * FROM mealsTable")
    fun getAllMeals() : LiveData<List<MealsMark>>

    @Query("SELECT COUNT(*) From mealsTable WHERE idMeal=:mealsId")
    fun isMarked(mealsId : Int) : Int

    @Query("SELECT EXISTS (SELECT 1 FROM mealsTable WHERE idMeal = :mealsId)")
    fun isExists(mealsId: Int) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg mealsMark: MealsMark)

    @Delete
    fun deleteMeals(mealsMark: MealsMark)

    @Query("DELETE FROM mealsTable WHERE idMeal=:mealsId")
    fun deleteById(mealsId: Int) : Int
}