package com.garsemar.plugins

import com.garsemar.routes.customerRouting
import com.garsemar.routes.homeRouting
import com.garsemar.routes.imageRouting
import com.garsemar.routes.movieRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        homeRouting()
        customerRouting()
        movieRouting()
        imageRouting()
    }
}
