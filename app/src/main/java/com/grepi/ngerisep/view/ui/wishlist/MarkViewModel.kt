package com.grepi.ngerisep.view.ui.wishlist

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.grepi.ngerisep.Repository
import com.grepi.ngerisep.db.MealsDatabase
import com.grepi.ngerisep.entity.MealsMark
import com.grepi.ngerisep.model.Meal
import kotlinx.coroutines.*
import java.sql.Array

class MarkViewModel constructor (application: Application) : AndroidViewModel(application) {

    private val repository : Repository = Repository(application)
    private val mealList : LiveData<List<MealsMark>> = repository.getMeals()

    fun fetchMeals() : LiveData<List<MealsMark>> {
        return mealList
    }

    fun insert(mealsMark: MealsMark) {
        repository.addMeals(mealsMark)
    }
}