package com.picpay.desafio.android.model

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.service.repository.PicPayRepository
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import rx.schedulers.Schedulers.io
import java.io.Serializable

class MainModel(val viewModel: MainViewModel,
                val repository: PicPayRepository,
                var users: List<User> = emptyList<User>()) : Serializable{

    fun onCreateView() {
        if (users.isEmpty()) {
            loadUsers()
        } else {
            viewModel.postUserList(users)
        }
    }

    fun loadUsers() {
        viewModel.showLoad()
        repository.getUsers().compose {
            it.subscribeOn(io())
        }.subscribe({
            users = arrayListOf()
            viewModel.hideLoad()
            if (it.isEmpty()) {
                viewModel.postEmptyList()
            }
            if (it.isNotEmpty()) {
                users = it
                saveUserList(users)
                viewModel.postUserList(users)
            }
        },{
            viewModel.hideLoad()
            viewModel.showLoadUserListError()
        })
    }

    private fun saveUserList(users: List<User>?) {

    }
}