package com.garsemar.plugins

import com.garsemar.routes.customerRouting
import com.garsemar.routes.homeRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        homeRouting()
        customerRouting()
    }
}
