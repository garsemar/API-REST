package com.garsemar.routes.filmWeb.templates

import io.ktor.server.html.*
import kotlinx.html.*

class LayoutTemplate : Template<HTML> {
    lateinit var content: String
    override fun HTML.apply() {
        head {
            title("Marti Times")
            link(rel = "stylesheet", href = "/static/film/main.css", type = "text/css")
            link(rel = "icon", href = "/static/film/favicon.ico", type = "image/x-icon")
        }
        body {
            img(classes = "headImg", src = "/static/film/MartiFilmsLogo.png") { width = "150px" }
            ul {
                li {
                    a {
                        href = "/films"
                        +"List movie"
                    }
                }
                li {
                    a {
                        href = "/films/new"
                        +"New Movie"
                    }
                }
                li {
                    a {
                        href = "/films/aboutus"
                        +"About Us"
                    }
                }
            }
            when (content) {
                "home" -> {
                    insert(HomeFilmsTemplate(), TemplatePlaceholder())
                }
                "newe" -> {
                    insert(NewFilmTemplate(), TemplatePlaceholder())
                }
                "film" -> {
                    insert(IdFilmTemplate(), TemplatePlaceholder())
                }
                "aboutus" -> {
                    insert(AboutUsFilmTemplate(), TemplatePlaceholder())
                }
            }
        }
    }
}