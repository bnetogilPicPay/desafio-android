package com.picpay.desafio.android.service.datasource

import com.picpay.desafio.android.service.api.PicPayApi

class PicPayDataSource(): AbstractDataSource() {
    private val api: PicPayApi by lazy {
        retrofit.create(PicPayApi::class.java)
    }

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}