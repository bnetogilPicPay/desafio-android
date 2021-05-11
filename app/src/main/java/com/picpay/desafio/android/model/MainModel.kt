package com.picpay.desafio.android.model

import com.picpay.desafio.android.data.User
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

                    users = arrayListOf(
                        User("https://randomuser.me/api/portraits/men/88.jpg",
                            "Usuario Um", 1, "userone"),
                        User("https://randomuser.me/api/portraits/men/30.jpg",
                            "Usuario Dois", 2, "usertwo"),
                        User("https://randomuser.me/api/portraits/men/81.jpg",
                            "Usuario Tres", 3, "userthree"),
                        User("https://randomuser.me/api/portraits/men/93.jpg",
                            "Usuario Quatro", 4, "userfour"),
                        User("https://randomuser.me/api/portraits/men/43.jpg",
                            "Usuario Quinto", 5, "userfive"))

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