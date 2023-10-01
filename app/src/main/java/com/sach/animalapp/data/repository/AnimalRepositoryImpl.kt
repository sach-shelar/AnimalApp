package com.sach.animalapp.data.repository

import com.sach.animalapp.data.model.AnimalModel
import com.sach.animalapp.data.web.AnimalApi
import javax.inject.Inject

class AnimalRepositoryImpl(private val api: AnimalApi) : AnimalRepository {
    override suspend fun fetchAnimalList(): List<AnimalModel> {
        return api.getAnimalList()
    }
}