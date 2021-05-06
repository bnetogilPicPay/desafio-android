package com.picpay.desafio.android.modules

import com.picpay.desafio.android.adapter.UserListAdapter
import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val components = module {
    factory { UserListAdapter() }
}

val picPayModules = module {
    viewModel { MainViewModel(get()) }
    viewModel { MainRepository() }
}
