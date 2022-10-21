package com.garsemar.routes

import com.garsemar.models.movie.Comment
import com.garsemar.models.movie.Movie
import com.garsemar.models.movie.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.movieRouting() {
    route("/api/movie") {
        get{
            if (movieStorage.isNotEmpty()) {
                call.respond(movieStorage)
            } else {
                call.respondText("No movies found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val movie =
                movieStorage.find { it.id == id } ?: return@get call.respondText(
                    "No movie with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(movie)
        }
        get("{id?}/comments") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val movie = commentStorage.filter { it.idMovie == id }
            call.respond(movie)
        }
        post{
            val movie = call.receive<Movie>()
            movieStorage.add(movie)
            call.respondText("Movie stored correctly", status = HttpStatusCode.Created)
        }
        post("{id?}"){
            val idMovie = call.parameters["id"] ?: return@post call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val comment = call.receive<Comment>()
            commentStorage.add(Comment(comment.id, idMovie, comment.text, comment.creationDate))
            call.respondText("Comment stored correctly", status = HttpStatusCode.Created)
        }
        put("{id?}") {
            val id = call.parameters["id"] ?: return@put call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val movie = call.receive<Movie>()
            for(i in movieStorage.indices){
                if(movieStorage[i].id == id){
                    movieStorage[i] = movie
                }
            }
            call.respondText("Movie edited correctly", status = HttpStatusCode.Accepted)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (movieStorage.removeIf { it.id == id } && commentStorage.removeIf { it.idMovie == id }) {
                call.respondText("Movie removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}