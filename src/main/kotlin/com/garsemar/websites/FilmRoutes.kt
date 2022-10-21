package com.garsemar.websites

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Route.filmRouting(){
    route("/films"){
        get {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "home"
            }
        }
    }
}

class LayoutTemplate: Template<HTML> {
    lateinit var content: String
    override fun HTML.apply() {
        head {
            title("Marti Times")
            link(rel = "stylesheet", href = "/static/film/main.css", type = "text/css")
            link(rel = "icon", href = "/static/film/favicon.ico", type="image/x-icon")
        }
        body {
            if(content == "home"){
                insert(HomeFilmsTemplate(), TemplatePlaceholder())
            }
        }
    }
}

class HomeFilmsTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {
        img ( classes="headImg", src="/static/film/MartiFilmsLogo.png")
    }
}