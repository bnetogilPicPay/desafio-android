package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityUserDetailBinding
import com.picpay.desafio.android.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        setSupportActionBar(binding.toolbar)

        configureSupportActionBar()
        loadParameters()
    }

    private fun configureSupportActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.str_details)
    }

    private fun loadParameters() {
        intent.getSerializableExtra("user")?.let {
            binding.content.user = it as User
            loadData(it as User)
        }
    }

    private fun loadData(user: User) {
        binding.content.progressBar.visibility = View.VISIBLE
        binding.content.picture.visibility = View.GONE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.content.picture, object : Callback {
                override fun onSuccess() {
                    binding.content.progressBar.visibility = View.GONE
                    binding.content.picture.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {

                }
            })
    }
}