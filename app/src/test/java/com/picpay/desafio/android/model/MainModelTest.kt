package com.picpay.desafio.android.model

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.model.MainModel
import com.picpay.desafio.android.service.datasource.PicPayDataSource
import com.picpay.desafio.android.service.repository.PicPayRepository
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.Subscriber

class MainModelTest {

    private lateinit var mainModel: MainModel
    private lateinit var viewModel: MainViewModel
    private lateinit var repository: PicPayRepository

    @Before
    fun setUp() {
        repository = mock()
        viewModel = MainViewModel()
        mainModel = MainModel(viewModel, repository)
    }

    @Test
    fun testLoadUsers() {
        // Given
        val responseList = arrayListOf(User("A" , "A", 1 , "A"),
            User("B" , "B", 2 , "B"))
        val expectedList = arrayListOf(User("A" , "A", 1 , "A"),
            User("B" , "B", 2 , "B"))

        val observable = Observable.create { subscriber: Subscriber<in List<User>>? -> }

        whenever(repository.getUsers()).then {
            mainModel.users = responseList.toList()
            observable
        }

        // When
        mainModel.loadUsers()

        // Then
        assertEquals(expectedList, mainModel.users)
    }

    @Test
    fun testEmptyLoadUsers() {
        // Given
        val arrayList = emptyList<User>()

        val observable = Observable.create {
                subscriber: Subscriber<in List<User>>? ->
            mainModel.users = arrayList.toList()
            subscriber?.onNext(mainModel.users)
        }

        whenever(repository.getUsers()).then {
            observable
        }

        // When
        mainModel.loadUsers()

        // Then
        assertEquals(arrayList, mainModel.users)
    }
}