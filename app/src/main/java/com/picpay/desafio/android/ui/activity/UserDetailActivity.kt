package com.picpay.desafio.android.ui.activity

import android.os.Bundle
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

        intent.getSerializableExtra("user")?.let {
            // user =  it as Serializable
            binding.content.user = it as User
            android.util.Log.e("UserDetail", "User: ${it as User}")
            Picasso.get()
                .load((it as User).img)
                .error(R.drawable.ic_round_account_circle)
                .into(binding.content.picture, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {

                    }
                })
        }

//        binding.content.name.text = "Novo Nome Editavel"
    }
}