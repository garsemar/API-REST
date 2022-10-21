package com.garsemar.models.customer

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String, var firstName: String, val lastName: String, val email: String)

val customerStorage = mutableListOf<Customer>(Customer("100", "Carlos", "Garcia", "carlos.garcia@gmail.com"), Customer("200", "Marti", "Garcia", "marti.garcia@gmail.com"))