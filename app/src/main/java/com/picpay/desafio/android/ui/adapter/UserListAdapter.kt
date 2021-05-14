package com.picpay.desafio.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.util.UserListDiffCallback
import com.picpay.desafio.android.ui.viewholder.UserListItemViewHolder
import com.picpay.desafio.android.ui.viewmodel.MainViewModel

class UserListAdapter(val viewModel: MainViewModel) : RecyclerView.Adapter<UserListItemViewHolder>() {

    var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = ListItemUserBinding.inflate(LayoutInflater.from(parent.context))

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])

        holder.view.itemUser.setOnClickListener {
            viewModel.contactClickMutable.postValue(users[position])
        }
    }

    override fun getItemCount(): Int = users.size
}