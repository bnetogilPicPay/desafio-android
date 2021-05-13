package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ServiceTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testLoadEmptyUserList() {
        // given
        val call = mock<Call<List<User>>>()
        val expectedUsers = emptyList<User>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
    }

    @Test
    fun testLoadDataUserList() {
        // given
        val call = mock<Call<List<User>>>()
        val expectedUsers = arrayListOf<User>(
            User("imagePath", "Tijs Maxim", 1, "@tijs"),
            User("imagePath","Yamin Marcin", 2, "@yamin"),
            User("imagePath","Yoram Affan", 3, "@yoram"),
            User("imagePath","Vladlen Guiomar", 4, "@vladlen")
        )

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
    }
}