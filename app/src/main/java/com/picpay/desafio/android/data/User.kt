package com.picpay.desafio.android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("img") var img: String,
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: Int,
    @SerializedName("username") var username: String
) : Serializable