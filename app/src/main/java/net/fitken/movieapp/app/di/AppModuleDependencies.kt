package net.fitken.movieapp.app.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.fitken.movieapp.base.navigation.NavManager
import retrofit2.Retrofit

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppModuleDependencies {

    fun navManager(): NavManager
    fun retrofit(): Retrofit
}