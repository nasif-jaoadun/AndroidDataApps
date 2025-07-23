package com.jnasif.androiddataapps.data

import com.squareup.moshi.Json

data class Monster (
    @field:Json(name = "monsterName") val name: String,
    val imageFile: String,
    val caption: String,
    val description: String,
    val price: Double,
    val scariness: Int
)