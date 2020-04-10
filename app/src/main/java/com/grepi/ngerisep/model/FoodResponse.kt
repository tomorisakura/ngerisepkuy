package com.grepi.ngerisep.model
import kotlinx.serialization.*

@Serializable
data class FoodResponse (
    val meals: List<Meal>
)