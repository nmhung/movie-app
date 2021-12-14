package net.fitken.feature.nowplaying.presentation

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import net.fitken.feature.nowplaying.data.DataModule
import net.fitken.feature.nowplaying.domain.DomainModule
import net.fitken.movieapp.app.di.AppModuleDependencies
import javax.inject.Singleton

@Component(
    dependencies = [AppModuleDependencies::class],
    modules = [DataModule::class, DomainModule::class, PresentationModule::class]
)
@Singleton
interface PresentationComponent {

    fun inject(fragment: NowPlayingFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(appModuleDependencies: AppModuleDependencies): Builder
        fun build(): PresentationComponent
    }
}