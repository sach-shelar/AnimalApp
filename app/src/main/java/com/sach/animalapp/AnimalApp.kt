package com.sach.animalapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/*
* Here @HiltAndroidApp triggers hilt's code generation.
* It create application-level dependency container.
*/
@HiltAndroidApp
class AnimalApp : Application() {}
