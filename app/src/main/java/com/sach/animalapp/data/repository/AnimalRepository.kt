package com.sach.animalapp.data.repository

import com.sach.animalapp.data.model.AnimalModel

interface AnimalRepository {
    suspend fun fetchAnimalList(): List<AnimalModel>
}