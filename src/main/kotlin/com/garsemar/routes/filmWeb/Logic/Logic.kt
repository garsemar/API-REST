package com.garsemar.routes.filmWeb.Logic

import com.garsemar.models.film.filmStorage

class Logic {
    fun getLastId(): String {
        val res = if(filmStorage.isNotEmpty()){
            filmStorage.maxOf { it.id }.toInt()+1
        }
        else{
            1
        }
        return res.toString()
    }
}