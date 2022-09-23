package com.garsemar.models

import kotlinx.serialization.Serializable

@Serializable
data class Movie(val id: String, val tittle: String)

/*
Pel·lícula: id, títol, any, gènere i director.
Comentari: id, id de la pel·lícula, comentari, data de creació.
 */