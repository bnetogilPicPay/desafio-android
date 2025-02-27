package com.picpay.desafio.android.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.model.MainModel
import com.picpay.desafio.android.service.repository.PicPayRepository
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
    private val repository: PicPayRepository by inject()

    private val model: MainModel by inject {
        parametersOf(viewModel, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureSupportActionBar()
        configureViewModelLiveData()
        configureAdapters()

        loadData()
    }

    private fun loadData() {
        if (model.users.isEmpty()) {
            model.loadUsers()
        } else {
            viewModel.postUserList(model.users)
        }
    }

    private fun configureSupportActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = resources.getString(R.string.title)
    }

    private fun configureViewModelLiveData() {
        viewModel.let {
            it.showLoadLiveData.observe(this, Observer {
                turnVisibleGoneProgress(it)
            })
            it.loadUsersListLiveData.observe(this, Observer {
                updateUserList(it)
            })
            it.errorOnLoadUserListLiveData.observe(this, Observer {
                showErrorOnLoadUserList()
            })
            it.loadUsersListEmptyLiveData.observe(this, Observer {
                showEmptyList()
            })
            it.contactClickLiveData.observe(this, Observer {
                openUserDetailActivity(it as User)
            })
        }
    }

    private fun showEmptyList() {

    }

    private fun showErrorOnLoadUserList() {

    }

    private fun openUserDetailActivity(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            putExtra("user", user as Serializable)
        }
        startActivity(intent)
    }

    private fun configureAdapters() {
        content.recyclerView.adapter = adapter
    }

    private fun updateUserList(userList: List<User>) {
        model.users = userList
        adapter.users = model.users
        adapter.notifyDataSetChanged()
    }

    private fun turnVisibleGoneProgress(visibility: Boolean) {
        content.userListProgressBar.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun hideLoading() {
        content.userListProgressBar.visibility = View.GONE
    }
}
