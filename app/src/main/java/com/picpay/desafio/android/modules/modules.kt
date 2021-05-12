package com.picpay.desafio.android.modules

import com.picpay.desafio.android.model.MainModel
import com.picpay.desafio.android.service.api.PicPayApi
import com.picpay.desafio.android.service.datasource.PicPayDataSource
import com.picpay.desafio.android.service.repository.PicPayRepository
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import com.picpay.desafio.android.ui.viewmodel.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val components = module {
    factory { (viewModel: MainViewModel) -> UserListAdapter(viewModel) }
    single { ( viewModel: MainViewModel, repository: PicPayRepository) -> MainModel(viewModel, repository) }
}

val picPayModules = module {
    viewModel { MainViewModel() }
    viewModel { UserDetailViewModel() }
}

val repositories = module {
    single { PicPayRepository(get()) }
}

val dataSources = module {
    single { PicPayDataSource() }
}