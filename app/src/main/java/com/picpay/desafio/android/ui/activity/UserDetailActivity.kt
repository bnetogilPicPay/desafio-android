package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityUserDetailBinding
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.databinding.ContentUserDetailBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity(R.layout.activity_user_detail) {
    private lateinit var contentBinding: ContentUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        bindingContentView()
        configureSupportActionBar()
        loadParameters()
    }

    private fun bindingContentView() {
        contentBinding = ContentUserDetailBinding.bind(content)
    }

    private fun configureSupportActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.str_details)
    }

    private fun loadParameters() {
        intent.getSerializableExtra("user")?.let {
            (it as User).let {
                turnOffEditable(it)

                contentBinding.user = it
            }

            loadData(it as User)
        }
    }

    private fun turnOffEditable(user: User) {
        user.enableUserName = false
        user.enableName = false
    }

    private fun turnOnEditable(user: User) {
        user.enableUserName = true
        user.enableName = true
    }

    private fun loadData(user: User) {
        contentBinding.progressBar.visibility = View.VISIBLE
        contentBinding.picture.visibility = View.GONE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(contentBinding.picture, object : Callback {
                override fun onSuccess() {
                    contentBinding.progressBar.visibility = View.GONE
                    contentBinding.picture.visibility = View.VISIBLE
                }
                override fun onError(e: Exception?) {

                }
            })
    }
}