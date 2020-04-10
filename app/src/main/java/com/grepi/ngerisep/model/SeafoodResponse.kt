package com.grepi.ngerisep.model

import com.google.gson.annotations.SerializedName

data class SeafoodResponse (
    @SerializedName("meals") var meals : List<Seafod>
)