package com.picpay.desafio.android.model

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.service.repository.PicPayRepository
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable
import rx.Subscriber

@RunWith(MockitoJUnitRunner::class)
class MainModelTest {

    @Mock
    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var repository: PicPayRepository

    private lateinit var mainModel: MainModel

    @Before
    fun setUp() {
        mainModel = MainModel(viewModel, repository)
    }

    private fun createResponseList() = listOf(
        User("A", "A", 1, "A"),
        User("B", "B", 2, "B")
    )

    @Test
    fun testLoadUsers() {
        // Given
        val responseList = createResponseList()
        val expectedList = arrayListOf(User("A" , "A", 1 , "A"),
            User("B" , "B", 2 , "B"))

        val observable = Observable.create { subscriber: Subscriber<in List<User>>? -> }

        whenever(repository.getUsers()).thenAnswer {
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
            mainModel.users = emptyList<User>()
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