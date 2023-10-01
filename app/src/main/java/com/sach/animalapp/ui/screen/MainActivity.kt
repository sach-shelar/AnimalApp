package com.sach.animalapp.ui.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.sach.animalapp.data.model.AnimalModel
import com.sach.animalapp.ui.theme.AnimalAppTheme
import com.sach.animalapp.view.MainIntent
import com.sach.animalapp.view.MainState
import com.sach.animalapp.view.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onButtonClick: () -> Unit = {
            lifecycleScope.launch {
                viewModel.userIntent.send(MainIntent.FetchAnimals)
            }
        }

        setContent {
            AnimalAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(vm = viewModel, onButtonClick = onButtonClick)
                }
            }
        }
    }
}


@Composable
fun MainScreen(vm: MainViewModel, onButtonClick: () -> Unit) {
    val state = vm.state.value
    when (state) {
        is MainState.Idle -> IdleScreen(onButtonClick)
        is MainState.Loading -> LoadingScreen()
        is MainState.Animals -> AnimalList(animals = state.animals)
        is MainState.Error -> {
            IdleScreen(onButtonClick)
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }
}

@Composable
fun IdleScreen(onButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onButtonClick) {
            Text(text = "Fetch Animals")
        }
    }
}

@Composable
fun LoadingScreen() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

}

@Composable
fun AnimalList(animals: List<AnimalModel>) {
    LazyColumn {
        items(items = animals) {
            AnimalItem(animal = it)
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
        }
    }
}

@Composable
fun AnimalItem(animal: AnimalModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        val painter = rememberAsyncImagePainter(animal.image)

        Image(
            painter = painter,
            contentDescription = animal.name,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillHeight
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp)
        ) {
            animal.name?.let { Text(text = it, fontWeight = FontWeight.Bold) }
            animal.location?.let { Text(text = it) }
        }

    }
}


