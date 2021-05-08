package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private lateinit var api: PicPayService

    private lateinit var service: ExampleService

    @Before
    fun setUp() {
        api = mock()
        service = ExampleService(api)
    }

    @Test
    fun testLoadEmptyUserList() {
        // given
        val call = mock<Call<List<User>>>()
        val expectedUsers = emptyList<User>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
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
        whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}