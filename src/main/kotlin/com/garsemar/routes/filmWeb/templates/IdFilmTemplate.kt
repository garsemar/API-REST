package com.garsemar.routes.filmWeb.templates

import com.garsemar.models.film.filmStorage
import com.garsemar.routes.filmWeb.id
import io.ktor.server.html.*
import kotlinx.html.*
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries

class IdFilmTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"Film" }
        val film = filmStorage.find { it.id == id }
        if(film != null){
            table("list") {
                tr {
                    td { img (src = "img/${film.image}"){ width="100px" } }
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