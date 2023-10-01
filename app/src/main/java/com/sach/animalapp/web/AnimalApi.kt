package com.sach.animalapp.web

import com.sach.animalapp.model.AnimalModel
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimalList(): List<AnimalModel>

}