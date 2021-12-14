package net.fitken.feature.toprated.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.fitken.feature.toprated.data.network.service.TopRatedService
import net.fitken.feature.toprated.domain.repository.TopRatedRepository
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideTopRatedService(retrofit: Retrofit): TopRatedService =
        retrofit.create(TopRatedService::class.java)

    @Provides
    fun provideTopRatedRepository(topRatedService: TopRatedService): TopRatedRepository =
        TopRatedRepositoryImpl(topRatedService)
}