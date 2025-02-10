package com.example.android.picsumsample.data

import com.example.android.picsumsample.data.PicsumResult.Fail
import com.example.android.picsumsample.data.PicsumResult.Loading
import com.example.android.picsumsample.data.PicsumResult.Success
import com.example.android.picsumsample.data.PicsumResult.Uninitialized
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Wrapper class representing the result of API call, including various states:
 * - [Uninitialized]: Initial state before a call is made.
 * - [Loading]: Indicates the API call is in progress.
 * - [Success]: Contains the successful result of API call.
 * - [Fail]: Contains the error encountered during API call.
 */
sealed class PicsumResult<out T>(private val value: T?) {
    open operator fun invoke(): T? = value

    data object Uninitialized : PicsumResult<Nothing>(value = null)
    data class Loading<out T>(private val value: T? = null) : PicsumResult<T>(value = null)
    data class Success<out T>(private val value: T) : PicsumResult<T>(value = value) {
        override operator fun invoke(): T = value
    }

    data class Fail<out T>(
        val throwable: Throwable, private val value: T? = null
    ) : PicsumResult<T>(value = value)
}

/**
 * Executes an API call and wraps the result in a [PicsumResult].
 * If the call is successful, it returns [PicsumResult.Success].
 * If an error occurs, it captures the exception and returns [PicsumResult.Fail].
 *
 * @param coroutineContext The coroutine context to run the API call in.
 * @param call The suspend function representing the API call.
 * @return A [PicsumResult] indicating the outcome of the API call.
 */
suspend fun <T> attemptHttp(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    call: suspend () -> T,
): PicsumResult<T> = try {
    withContext(coroutineContext) { Success(call()) }
} catch (e: Exception) {
    Fail(e)
}