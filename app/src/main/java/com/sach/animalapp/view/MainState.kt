package com.sach.animalapp.view

import com.sach.animalapp.data.model.AnimalModel

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Animals(val animals: List<AnimalModel>) : MainState()
    data class Error(val error: String?) : MainState()
}
