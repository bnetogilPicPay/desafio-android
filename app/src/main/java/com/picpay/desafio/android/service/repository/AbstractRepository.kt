package com.picpay.desafio.android.service.repository

import retrofit2.Call
import rx.Subscriber

open class AbstractRepository<T> {
    protected fun <T> callResponse(
        callResponse: Call<T>,
        subscriber: Subscriber<in T>
    ) {
        val response = callResponse.execute()

        if (response.isSuccessful) {
            val dataResponse = response.body()

            subscriber.onNext(dataResponse)
            subscriber.onCompleted()
        } else {
            val throwable: Throwable = if (response.errorBody() != null) {
                Throwable(
                    message =  response.errorBody()?.let { it.string() },
                    cause = Throwable(response.code().toString())
                )
            } else {
                Throwable(
                    message = response.message(),
                    cause = Throwable(response.code().toString())
                )
            }

            subscriber.onError(throwable)
        }
    }
}
