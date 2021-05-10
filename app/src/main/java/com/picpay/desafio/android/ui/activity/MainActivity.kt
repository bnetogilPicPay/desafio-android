package com.picpay.desafio.android.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.MainModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.service.PicPayService
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

    private val repository: MainRepository by inject()
    private val viewModel: MainViewModel by inject()

    private val service: PicPayService by lazy {
        repository.picPayService
    }

    private val model: MainModel by inject {
        parametersOf(viewModel, service)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureSupportActionBar()
        configureViewModelLiveData()
        configureAdapter()

        loadData(savedInstanceState)
    }

    private fun loadData(savedInstanceState: Bundle?) {
        existSerializableData(savedInstanceState)?.let {
            savedInstanceState?.getSerializable("Data")?.let {
                model.users = it as List<User>
                adapter.users = model.users
                adapter.notifyDataSetChanged()

                hideLoading()
            }
        }

        if (model.users.isEmpty()) {
            showLoading()
            model.loadUsers()
        }
    }

    private fun existSerializableData(savedInstanceState: Bundle?): Boolean? {
        return savedInstanceState?.containsKey("Data")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("Data", model.users as Serializable)
        super.onSaveInstanceState(outState)
    }

    private fun configureSupportActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = resources.getString(R.string.title)
    }

    private fun configureViewModelLiveData() {
        viewModel.let {
            it.loadUsersListLiveData.observe(this, Observer {
                hideLoading()
                updateUserList(it)
            })
            it.loadUsersLiveDataError.observe(this, Observer {
                hideLoading()
            })
            it.loadUsersListEmptyLiveData.observe(this, Observer {
                hideLoading()
            })
            it.contactClickLiveData.observe(this, Observer {
                android.util.Log.e("MainActivity", "userclick: $it")
                openUserDetailActivity(it as User)
            })
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
        model.users = userList
        adapter.users = model.users
        adapter.notifyDataSetChanged()


    }

    private fun showLoading() {
        content.userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        content.userListProgressBar.visibility = View.GONE
    }
}
