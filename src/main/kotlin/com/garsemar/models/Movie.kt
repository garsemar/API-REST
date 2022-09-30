package com.garsemar.models

import kotlinx.serialization.Serializable

@Serializable
data class Movie(val id: String, val tittle: String, val year: String, val genre: String, val director: String)

@Serializable
data class Comment(val id: String, val idMovie: String, val text: String, val creationDate: String)

val movieStorage = mutableListOf<Movie>(Movie("1", "Matrix", "1998", "Action", "Martin Garrix"))

val commentStorage = mutableListOf<Comment>(Comment("1", "1", "Besto movi", "2022"), Comment("2", "1", "Best movie", "2021"))