package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.modules.components
import com.picpay.desafio.android.modules.dataSources
import com.picpay.desafio.android.modules.picPayModules
import com.picpay.desafio.android.modules.repositories

import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(arrayListOf(components, picPayModules, dataSources, repositories))
        }
    }
}
