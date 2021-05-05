package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.MainRepository
import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.User
import com.picpay.desafio.android.datasource.BaseDataSource
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainViewModel(private val repository: MainRepository): ViewModel(), Serializable {

    var users = emptyList<User>()

    private val service: PicPayService by lazy {
        repository.picPayService
    }

    fun getUsers() {
        service.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    android.util.Log.e("ViewModel", "Received: ${response.body()}" )
                    response.body()?.let {
                        users = it
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    android.util.Log.e("ViewModel", "Failed...." )
                }

            })
    }
}