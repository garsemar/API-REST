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
        get("new") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "new"
            }
        }
        get("aboutus") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = "aboutus"
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
            img ( classes="headImg", src="/static/film/MartiFilmsLogo.png"){width = "150px"}
            ul {
                li {
                    a {
                        href = ""
                        + "List movie"
                    }
                }
                li {
                    a {
                        href = ""
                        + "New Movie"
                    }
                }
                li {
                    a {
                        href = "/films/aboutus"
                        + "About Us"
                    }
                }
            }
            if(content == "home"){
                insert(HomeFilmsTemplate(), TemplatePlaceholder())
            }
            else if(content == "new"){
                insert(HomeFilmsTemplate(), TemplatePlaceholder())
            }
        }
    }
}

class HomeFilmsTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { + """Llistat"""}
        table("list") {
            tr {
                td { + """img"""}
                td { + """text"""}
                td { + """text"""}
            }
            tr {
                td { + """img"""}
                td { + """text"""}
                td { + """text"""}
            }
            tr {
                td { + """img"""}
                td { + """text"""}
                td { + """text"""}
            }
        }
    }
}
class NewFilmTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {

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