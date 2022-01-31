package com.eliane.myapplication

import android.app.Application
import com.eliane.myapplication.api.RickMorthAPI
import com.eliane.myapplication.koinmodules.imageUtilsModule
import com.eliane.myapplication.koinmodules.serverModule
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyCustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            //androidLogger()
            androidContext(this@MyCustomApplication)
            modules(
                imageUtilsModule,
                serverModule,
            )
        }
    }
}