package com.picpay.desafio.android.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.view.*
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

        configureSupportActionBar()
        configureViewModelLiveData()
        configureAdapter()
        showLoading()

        viewModel.loadUsers()
    }

    private fun configureSupportActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = resources.getString(R.string.title)
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
                openUserDetailActivity(it as User)
            }
        }
    }

    private fun openUserDetailActivity(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            putExtra("user", user as Serializable)
        }
        startActivity(intent)
    }

    private fun configureAdapter() {
        content.recyclerView.adapter = adapter
    }

    private fun updateUserList(userList: List<User>) {
        viewModel.users = userList
        adapter.users = viewModel.users
        adapter.notifyDataSetChanged()

        hideLoading()
    }

    private fun showLoading() {
        content.userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        content.userListProgressBar.visibility = View.GONE
    }
}
