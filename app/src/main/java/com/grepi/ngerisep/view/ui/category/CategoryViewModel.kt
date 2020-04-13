package com.grepi.ngerisep.view.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.GsonBuilder
import com.grepi.ngerisep.model.FoodResponse
import com.grepi.ngerisep.model.Seafod
import com.grepi.ngerisep.model.SeafoodResponse
import com.grepi.ngerisep.static.Common
import org.json.JSONObject

class CategoryViewModel : ViewModel() {

    private lateinit var mFoodResponse : SeafoodResponse
    private var mFood : ArrayList<Seafod> = arrayListOf()
    private var mFoodList = MutableLiveData<ArrayList<Seafod>>()

    internal fun fetchCategoryFood(query : String) {
        AndroidNetworking.get(Common.url_category+query)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    mFoodResponse = GsonBuilder().create().fromJson(response.toString(), SeafoodResponse::class.java)
                    mFoodResponse.meals.forEach {
                        mFood.add(it)
                    }
                    mFoodList.postValue(mFood)
                    Log.d("_responseCategory", mFoodList.value.toString())
                }

                override fun onError(anError: ANError?) {
                    Log.e("_responseCategory", anError.toString())
                }
            })
    }

    internal fun getCategoryFood() : LiveData<ArrayList<Seafod>> {
        return mFoodList
    }
}