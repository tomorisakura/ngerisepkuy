package com.grepi.ngerisep.view.ui.home

import android.util.Log
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
import org.json.JSONArray
import org.json.JSONObject

class HomeViewModel : ViewModel() {

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

    internal fun fetchData() {

        AndroidNetworking.get(Common.url_dessert)
            .addQueryParameter("limit", "5")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject( object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    dessertResponse = GsonBuilder().create().fromJson(response.toString(), DessertResponse::class.java)
                    for (i in dessertResponse.meals.indices) {
                        dessert.add(dessertResponse.meals[i])
                    }
                    dessertList.postValue(dessert)
                    Log.d(Common.TAG, dessertList.value.toString())
                }

                override fun onError(anError: ANError?) {
                    Log.d("_responseError", anError.toString())
                }
            })
    }

    internal fun getMeals() : LiveData<ArrayList<Dessert>> {
        return dessertList
    }

    internal fun fetchSeafood() {
        AndroidNetworking.get(Common.url_seafood)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    seafoodResponse = GsonBuilder().create().fromJson(response.toString(), SeafoodResponse::class.java)
                    for (i in seafoodResponse.meals.indices) {
                        seafod.add(seafoodResponse.meals[i])
                    }
                    seafoodList.postValue(seafod)
                    Log.d("_responseSeafood", seafoodList.value.toString())
                }

                override fun onError(anError: ANError?) {
                    Log.d("_responseFailed", anError.toString())
                }

            })
    }

    internal fun getSeafood() : LiveData<ArrayList<Seafod>> {
        return seafoodList
    }

    internal fun fetchMealsbyId(id : String?) {
        AndroidNetworking.get(Common.url_byID+id)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    meals = GsonBuilder().create().fromJson(response.toString(), FoodResponse::class.java)
                    for (i in meals.meals.indices) {
                        food.add(meals.meals[i])
                    }
                    mealsList.postValue(food)
                }

                override fun onError(anError: ANError?) {
                    Log.d("_responseFailed", anError.toString())
                }

            })
    }

    internal fun getMealsById() : LiveData<ArrayList<Meal>> {
        return mealsList
    }

    internal fun fetchMisceFood() {
        AndroidNetworking.get(Common.url_misce)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    miscellaneousResponse = GsonBuilder().create().fromJson(response.toString(), MiscellaneousResponse::class.java)
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