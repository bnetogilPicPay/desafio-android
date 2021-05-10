package com.picpay.desafio.android.modules

import com.picpay.desafio.android.model.MainModel
import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import com.picpay.desafio.android.ui.viewmodel.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val components = module {
    factory { (viewModel: MainViewModel) -> UserListAdapter(viewModel) }
}

val picPayModules = module {
    viewModel { MainViewModel(get()) }
    factory { MainRepository() }
    factory { ( viewModel: MainViewModel, service: PicPayService) -> MainModel(viewModel, service) }
    viewModel { UserDetailViewModel() }
}
