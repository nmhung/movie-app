package net.fitken.feature.nowplaying.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.fitken.feature.nowplaying.data.network.NowPlayingRepositoryImpl
import net.fitken.feature.nowplaying.data.network.service.NowPlayingService
import net.fitken.feature.nowplaying.domain.repository.NowPlayingRepository
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideNowPlayingService(retrofit: Retrofit): NowPlayingService =
        retrofit.create(NowPlayingService::class.java)

    @Provides
    fun provideNowPlayingRepository(nowPlayingService: NowPlayingService): NowPlayingRepository =
        NowPlayingRepositoryImpl(nowPlayingService)
}