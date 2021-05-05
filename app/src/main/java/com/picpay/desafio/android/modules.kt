package com.picpay.desafio.android

import com.picpay.desafio.android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val picPayModules = module {
    viewModel { MainViewModel(get()) }
    viewModel { MainRepository() }
}
