package com.picpay.desafio.android.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    val view: ListItemUserBinding
) : RecyclerView.ViewHolder(view.root) {

    fun bind(user: User) {
        view.user = user
        showProgressBar()
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(view.picture, object : Callback {
                override fun onSuccess() {
                    hideProgressBar()
                }

                override fun onError(e: Exception?) {
                    hideProgressBar()
                }
            })
    }

    private fun showProgressBar() {
        view.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        view.progressBar.visibility = View.GONE
    }
}