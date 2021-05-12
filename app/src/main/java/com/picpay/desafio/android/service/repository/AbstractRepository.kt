package com.picpay.desafio.android.service.repository

import retrofit2.Call
import rx.Subscriber

open class AbstractRepository<T> {
    protected fun <T> callResponse(
        callResponse: Call<T>,
        subscriber: Subscriber<in T>
    ) {
        android.util.Log.e("Repositoy", "...execute...")
        val response = callResponse.execute()
        android.util.Log.e("Repositoy", "${response.isSuccessful}")
        if (response.isSuccessful) {
            val dataResponse = response.body()
            android.util.Log.e("Repositoy", "$dataResponse")
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
