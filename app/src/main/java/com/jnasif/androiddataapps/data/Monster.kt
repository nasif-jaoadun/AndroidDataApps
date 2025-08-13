package com.jnasif.androiddataapps.data

import com.jnasif.androiddataapps.IMAGE_BASE_URL
import com.squareup.moshi.Json

data class Monster (
    @field:Json(name = "monsterName") val name: String,
    val imageFile: String,
    val caption: String,
    val description: String,
    val price: Double,
    val scariness: Int
){
    val imageUrl
        get() = "$IMAGE_BASE_URL/$imageFile.webp"
    val thumbnailUrl
        get() = "$IMAGE_BASE_URL/${imageFile}_tn.webp"
}