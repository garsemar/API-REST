package com.garsemar.routes.filmWeb.templates

import io.ktor.server.html.*
import kotlinx.html.*

class NewFilmTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"New film" }
        form {
            encType = FormEncType.multipartFormData
            method = FormMethod.post
            action = "/films/addFilm"
            textInput(name = "tittle"){placeholder="Tittle"}
            textInput(name = "year"){placeholder="Year"}
            textInput(name = "genre"){placeholder="Genre"}
            textInput(name = "director"){placeholder="Director"}

            fileInput(name="image"){accept="image/png, image/jpeg"}
            submitInput{ id="button"; value="Add" }
        }
    }
}