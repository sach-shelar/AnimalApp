package com.sach.animalapp.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sach.animalapp.data.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: AnimalRepository) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    var state = mutableStateOf<MainState>(MainState.Idle)
        private set

    init {

        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchAnimals -> {
                        fetchAnimals()
                    }
                }
            }
        }

    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                MainState.Animals(repo.fetchAnimalList())
            } catch (e: Exception) {
                MainState.Error(e.message)
            }
        }

    }


}