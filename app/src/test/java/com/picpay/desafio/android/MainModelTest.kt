package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.model.MainModel
import com.picpay.desafio.android.repository.MainRepository
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class MainModelTest {

    private lateinit var mainModel: MainModel
    private lateinit var viewModel: MainViewModel
    private lateinit var service: PicPayService
    private lateinit var repository: MainRepository

    @Before
    fun setUp() {
        repository = MainRepository()
        service = mock()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun testLoadUsers() {
        // Given
        val call = mock<Call<List<User>>>()
        val arrayList = arrayListOf(
            User("A" , "A", 1 , "A"),
            User("B" , "B", 2 , "B"))

        mainModel = MainModel(viewModel, service)

        whenever(service.getUsers()).thenReturn(call)
        whenever(service.getUsers().enqueue(any())).then {
            arrayList.also {
                mainModel.users = it
            }
        }

        // When
        mainModel.loadUsers()

        // Then
        assertEquals(arrayList, mainModel.users)
    }

    @Test
    fun testEmptyLoadUsers() {
        // Given
        val call = mock<Call<List<User>>>()
        val arrayList = emptyList<User>()

        mainModel = MainModel(viewModel, service)

        whenever(service.getUsers()).thenReturn(call)
        whenever(service.getUsers().enqueue(any())).then {
            arrayList.also {
                mainModel.users = it
            }
        }

        // When
        mainModel.loadUsers()

        // Then
        assertEquals(arrayList.isEmpty(), mainModel.users.isEmpty())
    }
}