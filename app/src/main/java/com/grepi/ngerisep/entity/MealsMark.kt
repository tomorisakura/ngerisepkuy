package com.grepi.ngerisep.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "mealsTable")
data class MealsMark(
    @PrimaryKey @ColumnInfo(name = "idMeal") var idMeal: Double,
    @ColumnInfo(name = "strMeal") var strMeal: String?,
    @ColumnInfo(name = "strDrinkAlternate") var strDrinkAlternate: String? = null,
    @ColumnInfo(name = "strCategory") var strCategory: String?,
    @ColumnInfo(name = "strArea") var strArea: String?,
    @ColumnInfo(name = "strInstructions") var strInstructions: String?,
    @ColumnInfo(name = "strMealThumb") var strMealThumb: String?,
    @ColumnInfo(name = "strTags") var strTags: String?,
    @ColumnInfo(name = "strYoutube") var strYoutube: String?,
    @ColumnInfo(name = "strIngredient1") var strIngredient1: String?,
    @ColumnInfo(name = "strIngredient2") var strIngredient2: String?,
    @ColumnInfo(name = "strIngredient3") var strIngredient3: String?,
    @ColumnInfo(name = "strIngredient4") var strIngredient4: String?,
    @ColumnInfo(name = "strIngredient5") var strIngredient5: String?,
    @ColumnInfo(name = "strIngredient6") var strIngredient6: String?,
    @ColumnInfo(name = "strIngredient7") var strIngredient7: String?,
    @ColumnInfo(name = "strIngredient8") var strIngredient8: String?,
    @ColumnInfo(name = "strIngredient9") var strIngredient9: String?,
    @ColumnInfo(name = "strIngredient10") var strIngredient10: String?,
    @ColumnInfo(name = "strIngredient11") var strIngredient11: String?,
    @ColumnInfo(name = "strIngredient12") var strIngredient12: String?,
    @ColumnInfo(name = "strIngredient13") var strIngredient13: String?,
    @ColumnInfo(name = "strIngredient14") var strIngredient14: String?,
    @ColumnInfo(name = "strIngredient15") var strIngredient15: String?,
    @ColumnInfo(name = "strIngredient16") var strIngredient16: String? = null,
    @ColumnInfo(name = "strIngredient17") var strIngredient17: String? = null,
    @ColumnInfo(name = "strIngredient18") var strIngredient18: String? = null,
    @ColumnInfo(name = "strIngredient19") var strIngredient19: String? = null,
    @ColumnInfo(name = "strIngredient20") var strIngredient20: String? = null,
    @ColumnInfo(name = "strMeasure1") val strMeasure1: String?,
    @ColumnInfo(name = "strMeasure2") val strMeasure2: String?,
    @ColumnInfo(name = "strMeasure3") val strMeasure3: String?,
    @ColumnInfo(name = "strMeasure4") val strMeasure4: String?,
    @ColumnInfo(name = "strMeasure5") val strMeasure5: String?,
    @ColumnInfo(name = "strMeasure6") val strMeasure6: String?,
    @ColumnInfo(name = "strMeasure7") val strMeasure7: String?,
    @ColumnInfo(name = "strMeasure8") val strMeasure8: String?,
    @ColumnInfo(name = "strMeasure9") val strMeasure9: String?,
    @ColumnInfo(name = "strMeasure10") val strMeasure10: String?,
    @ColumnInfo(name = "strMeasure11") val strMeasure11: String?,
    @ColumnInfo(name = "strMeasure12") val strMeasure12: String?,
    @ColumnInfo(name = "strMeasure13") val strMeasure13: String?,
    @ColumnInfo(name = "strMeasure14") val strMeasure14: String?,
    @ColumnInfo(name = "strMeasure15") val strMeasure15: String?,
    @ColumnInfo(name = "strMeasure16") val strMeasure16: String?,
    @ColumnInfo(name = "strMeasure17") val strMeasure17: String?,
    @ColumnInfo(name = "strMeasure18") val strMeasure18: String?,
    @ColumnInfo(name = "strMeasure19") val strMeasure19: String?,
    @ColumnInfo(name = "strMeasure20") val strMeasure20: String?,
    @ColumnInfo(name = "strSource") var strSource: String?,
    @ColumnInfo(name = "dateModifed") var dateModifed: String?
) : Parcelable