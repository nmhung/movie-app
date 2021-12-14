package net.fitken.movieapp.app.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.fitken.movieapp.BuildConfig
import net.fitken.movieapp.app.data.network.BasicAuthInterceptor
import net.fitken.movieapp.base.interceptors.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger.Companion.DEFAULT
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun retrofitClient(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .build()
    }

    private fun moshi(): Moshi {
        val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory())
        return moshiBuilder.build()
    }

    private fun httpClient(context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        clientBuilder.addInterceptor(BasicAuthInterceptor())
        clientBuilder.addInterceptor(ConnectivityInterceptor(context))
        clientBuilder.connectTimeout(15, TimeUnit.SECONDS)
        clientBuilder.readTimeout(15, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(15, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    private fun createNetworkClient(context: Context): Retrofit {
        return retrofitClient(httpClient(context))
    }

    @Provides
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return createNetworkClient(context)
    }


}