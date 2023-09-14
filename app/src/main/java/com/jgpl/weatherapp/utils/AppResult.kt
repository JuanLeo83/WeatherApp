package com.jgpl.weatherapp.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed class AppResult<out T, out E> {
    data class Success<out T>(val data: T) : AppResult<T, Nothing>()
    data class Failure<out E>(val error: E) : AppResult<Nothing, E>()
}

fun resultSuccessEmpty() = AppResult.Success(Unit)

fun <T> resultSuccess(data: T) = AppResult.Success(data)

fun <E> resultFailure(error: E) = AppResult.Failure(error)

@OptIn(ExperimentalContracts::class)
inline fun <T, E> AppResult<T, E>.onSuccess(action: (T) -> Unit): AppResult<T, E> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

    if (this is AppResult.Success) action(data)
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T, E> AppResult<T, E>.onFailure(action: (E) -> Unit): AppResult<T, E> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

    if (this is AppResult.Failure) action(error)
    return this
}