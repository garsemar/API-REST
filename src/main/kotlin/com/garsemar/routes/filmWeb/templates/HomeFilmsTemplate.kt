package com.garsemar.routes.filmWeb.templates

import com.garsemar.models.film.filmStorage
import io.ktor.server.html.*
import kotlinx.html.*

class HomeFilmsTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"Films list" }
        table("list") {
            if(filmStorage.isNotEmpty()){
                filmStorage.forEach {
                    tr {
                        td { img (src="/films/uploads/${it.image}"){width="100px"} }
                        td { +it.tittle }
                        td { a (href="/films/${it.id}"){ +"Show details" } }
                    }
                }
            }
            else{
                h3 { +"No films stored" }
            }
        }
    }
}