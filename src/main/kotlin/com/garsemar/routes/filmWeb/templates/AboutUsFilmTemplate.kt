package com.garsemar.routes.filmWeb.templates

import io.ktor.server.html.*
import kotlinx.html.*

class AboutUsFilmTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"About Us" }
        table("list") {
            tr {
                td { +"Martí Garcia" }
                td { +"marti.garcia.7e4@itb.cat" }
            }
        }
    }
}