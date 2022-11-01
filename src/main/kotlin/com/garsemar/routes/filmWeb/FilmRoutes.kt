package com.garsemar.routes.filmWeb

import com.garsemar.models.film.*
import com.garsemar.routes.filmWeb.Logic.Logic
import com.garsemar.routes.filmWeb.templates.LayoutTemplate
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

lateinit var id: String

fun Route.filmRouting() {
    val logic = Logic()
    route("/films") {
        get {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "home"
            }
        }
        get("new") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "newe"
            }
        }
        get("{id}"){
            id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "film"
            }
        }
        get("aboutus") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "aboutus"
            }
        }
        post("addFilm"){
            val film = call.receiveParameters()
            filmStorage.add(Film(logic.getLastId(),film["tittle"]!!, film["year"]!!, film["genre"]!!, film["director"]!!))
            call.respondRedirect("/films/new")

            // En teoria esto es lo que hay que hacer
            /*val id = logic.getLastId()
            lateinit var tittle: String
            lateinit var year: String
            lateinit var genre: String
            lateinit var director: String
            lateinit var image: String
            val datos = call.receiveMultipart()
            datos.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "tittle" -> {
                                tittle = part.value
                            }
                            "year" -> {
                                year = part.value
                            }
                            "genre" -> {
                                genre = part.value
                            }
                            else -> {
                                director = part.value
                            }
                        }
                    }
                    is PartData.FileItem -> {
                        image = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        File("uploads/$image").writeBytes(fileBytes)
                    }
                    else -> {
                        println("Ns klk")
                    }
                }
            }

            val film = Film(id, tittle, year, genre, director, image)
            filmStorage.add(film)
            call.respondText("Film stored correctly and \"$image is uploaded to 'uploads/$image'\"", status = HttpStatusCode.Created)*/
        }
    }
}