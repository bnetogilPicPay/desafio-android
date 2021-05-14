package com.picpay.desafio.android.service.repository


import com.picpay.desafio.android.data.User
import rx.Observable
import com.picpay.desafio.android.service.api.PicPayApi
import com.picpay.desafio.android.service.datasource.PicPayDataSource

class PicPayRepository(private val dataDataSource: PicPayDataSource) : AbstractRepository<PicPayApi>() {

    private val picPayApi: PicPayApi by lazy {
        dataDataSource.createService(PicPayApi::class.java)
    }

    fun getUsers() : Observable<List<User>> = Observable.create {
        callResponse(picPayApi.getUsers(), it)
    }

}