package com.picpay.desafio.android.model

import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModel(val viewModel: MainViewModel,
                val service: PicPayService,
                var users: List<User> = emptyList<User>()) {

    fun loadUsers() {
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    users = arrayListOf()

                    response.body()?.let {
                        users = it
                    }

                    if (users.isEmpty()) {
                        viewModel.postEmptyList()
                    } else {
                        viewModel.postUserList(users)
                    }
                }

                override fun onFailure(call: Call<List<User>>, throwable: Throwable) {
                    viewModel.postloadUsersFailure()
                }
            })
    }
}