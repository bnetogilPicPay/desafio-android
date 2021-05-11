package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.service.PicPayService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository): ViewModel() {

    val loadUsersListLiveData : LiveData<List<User>> get() = loadUsersListMutable
    val loadUsersListEmptyLiveData : LiveData<Void?> get() = loadUsersListEmptyMutable
    val loadUsersLiveDataError : LiveData<Int> get() = loadUsersListMutableError
    private val loadUsersListMutable = MutableLiveData<List<User>>()
    private val loadUsersListEmptyMutable = MutableLiveData<Void?>()
    private val loadUsersListMutableError = MutableLiveData<Int>()

    val contactClickLiveData : LiveData<User> get() = contactClickMutable
    val contactClickMutable = MutableLiveData<User>()

    fun postUserList(users: List<User>) {
        loadUsersListMutable.postValue(users)
    }

    fun postEmptyList() {
        loadUsersListEmptyMutable.postValue(null)
    }

    fun postloadUsersFailure() {
        loadUsersListMutableError.postValue(-1)
    }
}