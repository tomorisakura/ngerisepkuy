package com.grepi.ngerisep.model

import kotlinx.serialization.Serializable

@Serializable
data class DessertResponse (
    val meals: List<Dessert>
)