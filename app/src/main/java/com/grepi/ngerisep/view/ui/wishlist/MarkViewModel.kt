package com.grepi.ngerisep.view.ui.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grepi.ngerisep.Repository
import com.grepi.ngerisep.db.MealsDatabase
import com.grepi.ngerisep.entity.MealsMark
import kotlinx.coroutines.launch

class MarkViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is wishlist Fragment"
    }
    val text: LiveData<String> = _text

}