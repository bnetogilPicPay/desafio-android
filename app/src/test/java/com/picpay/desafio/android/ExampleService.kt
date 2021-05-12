package com.picpay.desafio.android


import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.service.repository.PicPayRepository

class ExampleService(
    private val service: PicPayRepository
) {

    fun example(): List<User> {
        val users = service.getUsers().isEmpty()

        return emptyList()
    }
}