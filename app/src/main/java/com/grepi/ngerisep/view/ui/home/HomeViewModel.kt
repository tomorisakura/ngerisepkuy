package com.grepi.ngerisep.view.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.GsonBuilder
import com.grepi.ngerisep.model.*
import com.grepi.ngerisep.static.Common
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    lateinit var meals : FoodResponse
    lateinit var dessertResponse: DessertResponse
    lateinit var seafoodResponse : SeafoodResponse
    lateinit var miscellaneousResponse: MiscellaneousResponse
    var miscelaneous : ArrayList<Miscellaneous> = arrayListOf()
    var dessert : ArrayList<Dessert> = arrayListOf()
    var food : ArrayList<Meal> = arrayListOf()
    var seafod : ArrayList<Seafod> = arrayListOf()
    var misceList = MutableLiveData<ArrayList<Miscellaneous>>()
    var mealsList = MutableLiveData<ArrayList<Meal>>()
    var seafoodList = MutableLiveData<ArrayList<Seafod>>()
    var dessertList = MutableLiveData<ArrayList<Dessert>>()

    private suspend fun fetchData() {
        withContext(Dispatchers.IO) {
            AndroidNetworking.get(Common.url_dessert)
                .addQueryParameter("limit", "5")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject( object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        dessertResponse = GsonBuilder().create().fromJson(response.toString(), DessertResponse::class.java)
                        dessert.clear()
                        for (i in dessertResponse.meals.indices) {
                            dessert.add(dessertResponse.meals[i])
                        }
                        dessertList.value = dessert
                        Log.d(Common.TAG, dessertList.value.toString())
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("_responseError", anError.toString())
                    }
                })
        }
    }

    internal fun getMeals() : LiveData<ArrayList<Dessert>> {
        GlobalScope.launch(Dispatchers.IO) {
            fetchData()
        }
        return dessertList
    }

    private suspend fun fetchSeafood() {
        withContext(Dispatchers.IO) {
            AndroidNetworking.get(Common.url_seafood)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        seafoodResponse = GsonBuilder().create().fromJson(response.toString(), SeafoodResponse::class.java)
                        seafod.clear()
                        for (i in seafoodResponse.meals.indices) {
                            seafod.add(seafoodResponse.meals[i])
                        }
                        seafoodList.value = seafod
                        Log.d("_responseSeafood", seafoodList.value.toString())
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("_responseFailed", anError.toString())
                    }

                })
        }
    }

    internal fun getSeafood() : LiveData<ArrayList<Seafod>> {
        GlobalScope.launch(Dispatchers.IO) {
            fetchSeafood()
        }
        return seafoodList
    }

    internal suspend fun fetchMealsById(id : String?) {
        withContext(Dispatchers.IO) {
            AndroidNetworking.get(Common.url_byID+id)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        meals = GsonBuilder().create().fromJson(response.toString(), FoodResponse::class.java)
                        food.clear()
                        for (i in meals.meals.indices) {
                            food.add(meals.meals[i])
                        }
                        mealsList.value = food
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("_responseFailed", anError.toString())
                    }

                })
        }
    }

    internal fun getMealsById(id : String?) : LiveData<ArrayList<Meal>> {
        GlobalScope.launch(Dispatchers.IO) {
            fetchMealsById(id)
        }
        return mealsList
    }

    internal fun fetchMisceFood() {
        AndroidNetworking.get(Common.url_misce)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    miscellaneousResponse = GsonBuilder().create().fromJson(response.toString(), MiscellaneousResponse::class.java)
                    miscelaneous.clear()
                    miscellaneousResponse.meals.forEach {
                        miscelaneous.add(it)
                    }
                    misceList.postValue(miscelaneous)
                }

                override fun onError(anError: ANError?) {
                    Log.e("_responseMisce", anError.toString())
                }
            })
    }

    internal fun getMisceFood() : LiveData<ArrayList<Miscellaneous>> {
        return misceList
    }
}