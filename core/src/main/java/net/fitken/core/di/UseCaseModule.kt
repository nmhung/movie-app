package net.fitken.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.fitken.core.domain.repository.MapRepository
import net.fitken.core.domain.repository.MovieRepository
import net.fitken.core.domain.usecase.GetDirectionUseCase
import net.fitken.core.domain.usecase.GetMovieDetailsUseCase
import net.fitken.core.domain.usecase.GetNowPlayingUseCase
import net.fitken.core.domain.usecase.GetTopRatedUseCase
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

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetMapDirectionUseCase(mapRepository: MapRepository): GetDirectionUseCase =
        GetDirectionUseCase(mapRepository)
}