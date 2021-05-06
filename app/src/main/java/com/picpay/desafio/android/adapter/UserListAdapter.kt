package com.picpay.desafio.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.UserListDiffCallback
import com.picpay.desafio.android.viewholder.UserListItemViewHolder

class UserListAdapter() : RecyclerView.Adapter<UserListItemViewHolder>() {

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
            android.util.Log.e("UserList", "${users[position]}")
        }
    }

    override fun getItemCount(): Int = users.size
}