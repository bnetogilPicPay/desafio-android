package com.picpay.desafio.android

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.datasource.BaseDataSource
import java.io.Serializable

class MainRepository: ViewModel(), Serializable {


    private val dataSource: BaseDataSource by lazy {
        BaseDataSource("http://careers.picpay.com/tests/mobdev/")
    }

    val picPayService: PicPayService by lazy<PicPayService> {
        dataSource.createService(PicPayService::class.java)
    }

}