package com.jgpl.weatherapp.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T, R> {

    protected open fun dispatcher(): CoroutineContext = Dispatchers.IO

    fun prepare(input: T) = prepareFlow(input).flowOn(dispatcher())

    protected abstract fun prepareFlow(input: T): Flow<R>

}