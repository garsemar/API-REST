package com.garsemar.plugins

import com.garsemar.routes.*
import com.garsemar.routes.filmWeb.filmRouting
import com.garsemar.routes.homeRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        homeRouting()
        customerRouting()
        movieRouting()
        imageRouting()
        filmRouting()
        static("/static") {
            resources("static")
        }
    }
}
