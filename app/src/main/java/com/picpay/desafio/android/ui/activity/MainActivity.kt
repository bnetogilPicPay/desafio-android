package com.picpay.desafio.android.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.Serializable

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val adapter: UserListAdapter by inject() {
        parametersOf(viewModel)
    }

    private val viewModel: MainViewModel by inject()

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
            it.contactClickLiveData.observe(this) {
                val intent = Intent(this, UserDetailActivity::class.java).apply {
                    putExtra("user", it as Serializable)
                }
                startActivity(intent)
                android.util.Log.e("MainActivity", "Notify Open Screen: $it")
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
