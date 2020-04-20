package com.grepi.ngerisep

import android.content.Context
import androidx.lifecycle.*
import com.grepi.ngerisep.db.MealsDAO
import com.grepi.ngerisep.db.MealsDatabase
import com.grepi.ngerisep.entity.MealsMark
import kotlinx.coroutines.*

class Repository(context: Context) {

    private var mealsDAO: MealsDAO = MealsDatabase.getInstance(context).mealsMarkDAO()
    private val mealList : LiveData<List<MealsMark>> = mealsDAO.getAllMeals()

    fun addMeals(mealsMark: MealsMark) {
        GlobalScope.launch(Dispatchers.IO) {
            val init = async { mealsDAO.insertAll(mealsMark) }
            init.await()
        }
    }

    suspend fun isExitsMels(id: Int) : Int = withContext(Dispatchers.IO) {
        val init = async { mealsDAO.isExists(id) }
        init.await()
    }

    suspend fun isMarkedMeals(id : Int) : Int = withContext(Dispatchers.IO) {
        val init = async { mealsDAO.isMarked(id) }
        init.await()
    }

    fun deleteMeals(mealsMark: MealsMark) {
        GlobalScope.launch(Dispatchers.IO) {
            val init = async { mealsDAO.deleteMeals(mealsMark) }
            init.await()
        }
    }

    fun getMeals() : LiveData<List<MealsMark>> {
        return mealList
    }
}
