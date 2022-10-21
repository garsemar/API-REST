package com.garsemar.models.movie

import kotlinx.serialization.Serializable

@Serializable
data class Comment(val id: String, val idMovie: String, val text: String, val creationDate: String)

val commentStorage = mutableListOf<Comment>(Comment("1", "1", "Besto movi", "2022"), Comment("2", "1", "Best movie", "2021"))