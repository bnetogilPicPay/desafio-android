package com.picpay.desafio.android.modules

import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val components = module {
    factory { (viewModel: MainViewModel) -> UserListAdapter(viewModel) }
}

val picPayModules = module {
    viewModel { MainViewModel(get()) }
    viewModel { MainRepository() }
}
