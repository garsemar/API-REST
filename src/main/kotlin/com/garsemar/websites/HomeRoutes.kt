package com.garsemar.websites

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Route.homeRouting(){
    route("/"){
        get {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title("Hola")
                    link(rel = "stylesheet", href = "/static/main.css", type = "text/css")
                    link(rel = "icon", href = "/static/favicon.ico", type="image/x-icon")
                }
                body{
                    h1 { +"Hello World" }
                }
            }
        }
    }
}