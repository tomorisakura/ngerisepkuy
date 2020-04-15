package com.grepi.ngerisep

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.GsonBuilder
import com.grepi.ngerisep.db.MealsDAO
import com.grepi.ngerisep.db.MealsDatabase
import com.grepi.ngerisep.entity.MealsMark
import com.grepi.ngerisep.model.FoodResponse
import com.grepi.ngerisep.model.Meal
import com.grepi.ngerisep.static.Common
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class Repository {

    suspend fun addMeals(context : Context, mealsMark: MealsMark) = coroutineScope{
        val insertMeals = async { MealsDatabase.getInstance(context).mealsMarkDAO().insertAll(mealsMark) }
        insertMeals.await()
    }
}
