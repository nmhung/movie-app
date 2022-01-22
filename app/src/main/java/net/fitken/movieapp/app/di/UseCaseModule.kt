package net.fitken.movieapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import net.fitken.domain.repository.MapRepository
import net.fitken.domain.repository.MovieRepository
import net.fitken.domain.usecase.GetDirectionUseCase
import net.fitken.domain.usecase.GetMovieDetailsUseCase
import net.fitken.domain.usecase.GetNowPlayingUseCase
import net.fitken.domain.usecase.GetTopRatedUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideGetNowPlayingUseCase(movieRepository: MovieRepository): GetNowPlayingUseCase =
        GetNowPlayingUseCase(movieRepository)

    @ViewModelScoped
    @Provides
    fun provideGetTopRatedUseCase(movieRepository: MovieRepository): GetTopRatedUseCase =
        GetTopRatedUseCase(movieRepository)

    @ViewModelScoped
    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(movieRepository)

    @ViewModelScoped
    @Provides
    fun provideGetMapDirectionUseCase(mapRepository: MapRepository): GetDirectionUseCase =
        GetDirectionUseCase(mapRepository)
}