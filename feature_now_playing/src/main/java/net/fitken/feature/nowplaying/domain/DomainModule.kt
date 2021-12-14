package net.fitken.feature.nowplaying.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.fitken.feature.nowplaying.domain.repository.NowPlayingRepository
import net.fitken.feature.nowplaying.domain.usecase.GetNowPlayingUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideGetNowPlayingUseCase(nowPlayingRepository: NowPlayingRepository): GetNowPlayingUseCase =
        GetNowPlayingUseCase(nowPlayingRepository)
}