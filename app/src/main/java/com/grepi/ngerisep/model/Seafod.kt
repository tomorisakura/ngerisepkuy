package com.grepi.ngerisep.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Seafod(
    var strMeal : String?,
    var strMealThumb : String?,
    var idMeal : String?
) : Parcelable