package com.sach.animalapp.di

import com.google.gson.Gson
import com.sach.animalapp.web.AnimalApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideRetrofit(): AnimalApi {

        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimalApi::class.java)


    }

}