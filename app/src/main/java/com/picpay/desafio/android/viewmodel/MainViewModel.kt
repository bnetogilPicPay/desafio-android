package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainViewModel(private val repository: MainRepository): ViewModel(), Serializable {

    val userListLiveData : LiveData<List<User>> get() = userListMutable
    val userListLiveDataError : LiveData<Int> get() = userListMutableError
    private val userListMutable = MutableLiveData<List<User>>()
    private val userListMutableError = MutableLiveData<Int>()

    var users = emptyList<User>()

    private val service: PicPayService by lazy {
        repository.picPayService
    }

    fun loadUsers() {
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.body() == null) {
                        userListMutable.postValue(arrayListOf())
                    } else {
                        response.body()?.let {
                            userListMutable.postValue(it)
                        }
                    }
                }

                override fun onFailure(call: Call<List<User>>, throwable: Throwable) {
                    userListMutableError.postValue(-1)
                }
            })
    }
}