package com.garsemar.models.film

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Film(val id: String, val tittle: String, val year: String, val genre: String, val director: String, val image: String = "mono.jpg")

val filmStorage = mutableListOf<Film>()