package com.grepi.ngerisep.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryFoodResponse(
    var meals : List<CategoryFood>
)