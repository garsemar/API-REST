package com.garsemar.routes

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.imageRouting(){
    route("/api/image"){
        post {
            var id = ""
            var name = ""
            var fileName = ""
            val datos = call.receiveMultipart()
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
        }
        get("/uploads/{imageName}") {
            val imageName = call.parameters["imageName"]
            val file = File("./uploads/$imageName")
            if(file.exists()){
                call.respondFile(File("./uploads/$imageName"))
            }
            else{
                call.respondText("Image not found", status = HttpStatusCode.NotFound)
            }
        }

    }
}