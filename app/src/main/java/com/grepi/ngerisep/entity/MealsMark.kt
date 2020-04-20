package com.grepi.ngerisep.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "mealsTable")
data class MealsMark(
    @PrimaryKey @ColumnInfo(name = "idMeal") var idMeal: Int,
    @ColumnInfo(name = "strMeal") var strMeal: String?,
    @ColumnInfo(name = "strCategory") var strCategory: String?,
    @ColumnInfo(name = "strArea") var strArea: String?,
    @ColumnInfo(name = "strMealThumb") var strMealThumb: String?
) : Parcelable