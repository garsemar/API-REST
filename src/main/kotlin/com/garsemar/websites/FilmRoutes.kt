package com.garsemar.websites

import com.garsemar.models.customer.Customer
import com.garsemar.models.film.Film
import com.garsemar.models.film.filmStorage
import com.garsemar.models.image.Guy
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.io.File


fun Route.filmRouting() {
    route("/films") {
        get {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "home"
            }
        }
        get("newe") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "new"
            }
        }
        post("new"){
            val id = getLastId()
            lateinit var tittle: String
            lateinit var year: String
            lateinit var genre: String
            lateinit var director: String
            lateinit var image: String
            val filmList = mutableListOf<Film>()
            val datos = call.receiveMultipart()
            println(datos.readAllParts())
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

                    }
                }

            }
            val film = Film(id, tittle, year, genre, director, image)
            filmList.add(film)
            call.respondText("Film stored correctly and \"$image is uploaded to 'uploads/$image'\"", status = HttpStatusCode.Created)
        }
        get("aboutus") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "aboutus"
            }
        }
    }
}

fun getLastId(): String {
    val res = if(filmStorage.isNotEmpty()){
        filmStorage.maxOf { it.id }.toInt()+1
    }
    else{
        1
    }
    return res.toString()
}

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
            if (content == "home") {
                insert(HomeFilmsTemplate(), TemplatePlaceholder())
            } else if (content == "new") {
                insert(NewFilmTemplate(), TemplatePlaceholder())
            }
        }
    }
}

class HomeFilmsTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"Llistat" }
        table("list") {
            tr {
                td { +"img" }
                td { +"text" }
                td { +"text" }
            }

        }
    }
}

class NewFilmTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"Nova" }
        form {
            encType = FormEncType.multipartFormData
            method = FormMethod.post
            action = "/films/new"
            input {
                type = InputType.text
                placeholder = "Tittle"
            }
            input {
                type = InputType.text
                placeholder = "Year"
                value = "hola"
            }
            input {
                type = InputType.text
                placeholder = "Genre"
            }
            input {
                type = InputType.text
                placeholder = "Director"
            }
            input {
                type = InputType.file
                accept = "image/png, image/jpeg"
            }
            input {
                type = InputType.submit
                value = "Add"
                id = "button"
            }
        }
    }
}

/*
* <html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Document</title>
</head>
<body>
    <img src="foca.jpg" width="150px" class="headImg">
    <ul>
        <li>
            <a href="">lis1</a>
        </li>
        <li>
            <a href="">lis1</a>
        </li>
        <li>
            <a href="">lis1</a>
        </li>
    </ul>
    <h1>Llistat</h1>
    <table class="list">
        <tr>
            <td>img</td>
            <td>text</td>
            <td>text</td>
        </tr>
        <tr>
            <td>img</td>
            <td>text</td>
            <td>text</td>
        </tr>
        <tr>
            <td>img</td>
            <td>text</td>
            <td>text</td>
        </tr>
    </table>
</body>
</html>
* */