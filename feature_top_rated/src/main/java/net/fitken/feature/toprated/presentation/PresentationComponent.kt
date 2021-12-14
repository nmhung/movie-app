package net.fitken.feature.toprated.presentation

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import net.fitken.feature.toprated.data.DataModule
import net.fitken.feature.toprated.domain.DomainModule
import net.fitken.movieapp.app.di.AppModuleDependencies
import javax.inject.Singleton

@Component(
    dependencies = [AppModuleDependencies::class],
    modules = [DataModule::class, DomainModule::class]
)
@Singleton
interface PresentationComponent {

    fun inject(fragment: TopRatedFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(appModuleDependencies: AppModuleDependencies): Builder
        fun build(): PresentationComponent
    }
}