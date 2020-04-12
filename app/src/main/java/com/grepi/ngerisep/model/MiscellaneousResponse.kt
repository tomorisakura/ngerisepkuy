package com.grepi.ngerisep.model

import kotlinx.serialization.Serializable

@Serializable
data class MiscellaneousResponse(
    val meals : List<Miscellaneous>
)