package com.eliane.myapplication.koinmodules

import com.squareup.picasso.Picasso
import org.koin.dsl.module

val imageUtilsModule = module {

    single<Picasso> {
        Picasso.get()
    }
}
