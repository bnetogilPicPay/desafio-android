package com.picpay.desafio.android

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val adapter: UserListAdapter by inject()
    private val repository: MainRepository by inject()
    private val viewModel: MainViewModel by inject() {
        parametersOf(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate view and obtain an instance of the binding class.

        configureViewModelLiveData()
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

//        adapter = UserListAdapter(this.applicationContext)
        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE
        viewModel.loadUsers()
    }

    override fun onResume() {
        super.onResume()
        android.util.Log.e("MainActivity", "onResume")

//        service.getUsers()
//            .enqueue(object : Callback<List<User>> {
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    val message = getString(R.string.error)
//
//                    progressBar.visibility = View.GONE
//                    recyclerView.visibility = View.GONE
//
//                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    progressBar.visibility = View.GONE
//
//                    adapter.users = response.body()!!
//                }
//            })
    }

    private fun configureViewModelLiveData() {
        viewModel.let {
            it.userListLiveData.observe(this) {
                viewModel.users = it

                adapter.notifyDataSetChanged()
            }
        }
    }
}
