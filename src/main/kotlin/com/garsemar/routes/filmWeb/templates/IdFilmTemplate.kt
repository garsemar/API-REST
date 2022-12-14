package com.garsemar.routes.filmWeb.templates

import com.garsemar.models.film.filmStorage
import com.garsemar.routes.filmWeb.id
import io.ktor.server.html.*
import kotlinx.html.*

class IdFilmTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"Film" }
        val film = filmStorage.find { it.id == id }
        if(film != null){
            table("list") {
                tr {
                    td { img (src = "/films/uploads/${film.image}"){ width="100px" } }
                    td { +film.tittle }
                    td { +film.year }
                    td { +film.genre }
                    td { +film.director }
                }
            }
        }
        else{
            h3 { +"No film stored" }
        }
    }
}