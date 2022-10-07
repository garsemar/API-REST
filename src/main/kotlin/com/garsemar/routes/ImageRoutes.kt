package com.garsemar.routes

import com.garsemar.models.Guy
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.io.File

fun Route.imageRouting(){
    route("/api/image"){
        get("/{imageName}") {
            val imageName = call.parameters["imageName"]
            val file = File("./uploads/$imageName")
            if(file.exists()){
                call.respondFile(File("./uploads/$imageName"))
            }
            else{
                call.respondText("Image not found", status = HttpStatusCode.NotFound)
            }
        }
        get {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title("Item List")
                }
                body{
                    h1 { +"Hello World" }
                }
            }
        }
        post {
            lateinit var id: String
            lateinit var name: String
            lateinit var fileName: String
            val guyList = mutableListOf<Guy>()
            val datos = call.receiveMultipart()
            println(datos)
            datos.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        if (part.name == "id") {
                            id = part.value
                        } else {
                            name = part.value
                        }
                    }
                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        File("uploads/$fileName").writeBytes(fileBytes)
                    }
                    else -> {

                    }
                }

            }
            val guy = Guy(id, name, fileName)
            guyList.add(guy)
            call.respondText("Guy stored correctly and \"$fileName is uploaded to 'uploads/$fileName'\"", status = HttpStatusCode.Created)
        }
    }
}