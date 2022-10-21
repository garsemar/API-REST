package com.garsemar.models.movie

import kotlinx.serialization.Serializable

@Serializable
data class Movie(val id: String, val tittle: String, val year: String, val genre: String, val director: String)

val movieStorage = mutableListOf<Movie>(Movie("1", "Matrix", "1998", "Action", "Martin Garrix"))