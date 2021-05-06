package com.picpay.desafio.android.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.picpay.desafio.android.R
import com.picpay.desafio.android.adapter.UserListAdapter
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val adapter: UserListAdapter by inject()
    private val repository: MainRepository by inject()
    private val viewModel: MainViewModel by inject() {
        parametersOf(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureViewModelLiveData()
        configureView()
        showLoading()

        viewModel.loadUsers()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun configureViewModelLiveData() {
        viewModel.let {
            it.loadUsersListLiveData.observe(this) {
                updateUserList(it)
            }
            it.loadUsersLiveDataError.observe(this) {
                // TODO: Add Failed Message
            }
        }
    }

    private fun configureView() {
        recyclerView.adapter = adapter
    }

    private fun updateUserList(userList: List<User>) {
        viewModel.users = userList
        adapter.users = viewModel.users
        adapter.notifyDataSetChanged()

        hideLoading()
    }

    private fun showLoading() {
        userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        userListProgressBar.visibility = View.GONE
    }
}
