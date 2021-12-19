package net.fitken.movieapp.app.di

import android.content.Context
import android.location.Geocoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.fitken.movieapp.base.navigation.NavManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavManager(): NavManager = NavManager()

    @Singleton
    @Provides
    fun provideGeocoder(@ApplicationContext context: Context) : Geocoder = Geocoder(context)
}