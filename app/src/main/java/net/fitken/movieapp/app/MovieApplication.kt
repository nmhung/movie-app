package net.fitken.movieapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.fitken.movieapp.BuildConfig
import timber.log.Timber


@HiltAndroidApp
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}