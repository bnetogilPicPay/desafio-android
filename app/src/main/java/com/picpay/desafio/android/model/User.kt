package com.picpay.desafio.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class User(
    @SerializedName("img") var img: String,
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: Int,
    @SerializedName("username") var username: String
) : Parcelable, Serializable