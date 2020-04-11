package com.grepi.ngerisep.view.ui.activity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import com.grepi.ngerisep.model.FoodResponse
import com.grepi.ngerisep.model.Meal
import com.grepi.ngerisep.static.Common
import org.json.JSONObject

class SearchViewModel : ViewModel() {

    private lateinit var mealResponse : FoodResponse
    private val mealList = MutableLiveData<ArrayList<Meal>>()
    private val meal : ArrayList<Meal> = arrayListOf()


    internal fun fetchSearchFood(query : String, context: Context) {
        AndroidNetworking.get(Common.url_searchFood+query)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    mealResponse = GsonBuilder().create().fromJson(response.toString(), FoodResponse::class.java)
                    if (!mealResponse.meals.isNullOrEmpty()) {
                        mealResponse.meals.forEach {
                            meal.add(it)
                        }
                        mealList.postValue(meal)
                    } else {
                        Toast.makeText(context, "Resep tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("_responseFailure", anError.toString())
                }

            })
    }

    internal fun getSearchFood() : LiveData<ArrayList<Meal>> {
        return mealList
    }
}