package com.picpay.desafio.android.repository

import com.picpay.desafio.android.datasource.BaseDataSource
import com.picpay.desafio.android.service.PicPayService
import java.io.Serializable

class MainRepository: Serializable {
    private val dataSource: BaseDataSource by lazy {
        BaseDataSource()
    }

    val picPayService: PicPayService by lazy<PicPayService> {
        dataSource.createService(PicPayService::class.java)
    }

}