package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.User

class MainViewModel: ViewModel() {
    val loadUsersListLiveData : LiveData<List<User>> get() = loadUsersListMutable
    val loadUsersListEmptyLiveData : LiveData<Void?> get() = loadUsersListEmptyMutable
    val errorOnLoadUserListLiveData : LiveData<Int> get() = showErrorOnLoadUserListMutable
    private val loadUsersListMutable = MutableLiveData<List<User>>()
    private val loadUsersListEmptyMutable = MutableLiveData<Void?>()
    private val showErrorOnLoadUserListMutable = MutableLiveData<Int>()

    val contactClickLiveData : LiveData<User> get() = contactClickMutable
    val contactClickMutable = MutableLiveData<User>()

    val showLoadMutable = MutableLiveData<Boolean>()
    val showLoadLiveData: LiveData<Boolean> get() = showLoadMutable

    fun postUserList(users: List<User>) {
        loadUsersListMutable.postValue(users)
    }

    fun postEmptyList() {
        loadUsersListEmptyMutable.postValue(null)
    }

    fun showLoadUserListError() {
        showErrorOnLoadUserListMutable.postValue(-1)
    }

    fun showLoad() {
        showLoadMutable.postValue(true)
    }

    fun hideLoad() {
        showLoadMutable.postValue(false)
    }
}