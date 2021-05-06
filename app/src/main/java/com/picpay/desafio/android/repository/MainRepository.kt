package com.picpay.desafio.android.repository

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.datasource.BaseDataSource
import java.io.Serializable

class MainRepository: ViewModel(), Serializable {

    private val dataSource: BaseDataSource by lazy {
        BaseDataSource()
    }

    val picPayService: PicPayService by lazy<PicPayService> {
        dataSource.createService(PicPayService::class.java)
    }

}