package com.picpay.desafio.android.model

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.service.repository.PicPayRepository
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

class MainModel(val viewModel: MainViewModel,
                val repository: PicPayRepository,
                var users: List<User> = emptyList<User>()) {

    fun loadUsers() {
        repository.getUsers().compose {
            it.subscribeOn(io())
        }.subscribe({
            users = arrayListOf()
            if (it.isEmpty()) {
                viewModel.postEmptyList()
            }
            if (it.isNotEmpty()) {
                users = it
                viewModel.postUserList(users)
            }
        },{
            viewModel.postloadUsersFailure()
        })
    }
}