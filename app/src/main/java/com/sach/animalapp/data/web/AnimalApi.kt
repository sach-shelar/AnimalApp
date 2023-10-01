package com.sach.animalapp.data.web

import com.sach.animalapp.data.model.AnimalModel
import retrofit2.http.GET

interface AnimalApi {

    @GET("animal.json")
    suspend fun getAnimalList(): List<AnimalModel>

}