package com.sach.animalapp.di

import com.google.gson.Gson
import com.sach.animalapp.data.repository.AnimalRepository
import com.sach.animalapp.data.repository.AnimalRepositoryImpl
import com.sach.animalapp.data.web.AnimalApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideRetrofit(): AnimalApi {

        return Retrofit.Builder()
            .baseUrl("https://sach-shelar.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimalApi::class.java)


    }

    @Singleton
    @Provides
    fun provideRepository(api: AnimalApi):AnimalRepository{
        return AnimalRepositoryImpl(api)
    }

}