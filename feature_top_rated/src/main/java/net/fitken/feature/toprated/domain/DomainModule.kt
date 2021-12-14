package net.fitken.feature.toprated.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.fitken.feature.toprated.domain.repository.TopRatedRepository
import net.fitken.feature.toprated.domain.usecase.GetTopRatedUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideGetTopRatedUseCase(topRatedRepository: TopRatedRepository): GetTopRatedUseCase =
        GetTopRatedUseCase(topRatedRepository)
}