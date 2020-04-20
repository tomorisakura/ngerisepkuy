package com.grepi.ngerisep.view.ui.wishlist

import android.app.Application
import androidx.lifecycle.*
import com.grepi.ngerisep.Repository
import com.grepi.ngerisep.entity.MealsMark
import kotlinx.coroutines.*

class MarkViewModel constructor (application: Application) : AndroidViewModel(application) {

    private val repository : Repository = Repository(application)
    private val mealList : LiveData<List<MealsMark>> = repository.getMeals()
    private var status = MediatorLiveData<Boolean>()
    private var btnStatus = MutableLiveData<Boolean>()

    fun fetchMeals() : LiveData<List<MealsMark>> {
        return mealList
    }

    fun getExists() : LiveData<Boolean> {
        return status
    }

    fun checkIdMeals(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val mealsId = repository.isExitsMels(id)
            if (mealsId != 1) {
                btnStatus.postValue(true)
            } else {
                btnStatus.postValue(false)
            }
        }
    }

    fun getCheck() : LiveData<Boolean> {
        return btnStatus
    }

    fun isMarkList(mealsMark: MealsMark, id : Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val count = repository.isMarkedMeals(id)
            if (count <= 0) {
                repository.addMeals(mealsMark)
                status.postValue(true)
            } else {
                repository.deleteMeals(mealsMark)
                status.postValue(false)
            }
        }
    }
}