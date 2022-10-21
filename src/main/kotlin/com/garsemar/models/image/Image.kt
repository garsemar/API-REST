package com.garsemar.models.image

import kotlinx.serialization.Serializable

@Serializable
data class Guy(val id: String, val name: String, val image: String)