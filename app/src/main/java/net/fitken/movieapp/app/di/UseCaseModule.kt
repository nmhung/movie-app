package net.fitken.movieapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.fitken.domain.repository.MovieRepository
import net.fitken.domain.usecase.GetNowPlayingUseCase
import net.fitken.domain.usecase.GetTopRatedUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetNowPlayingUseCase(movieRepository: MovieRepository): GetNowPlayingUseCase =
        GetNowPlayingUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetTopRatedUseCase(movieRepository: MovieRepository): GetTopRatedUseCase =
        GetTopRatedUseCase(movieRepository)
}