package com.example.domain.usecases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type,in Params>{
    abstract suspend fun run(params: Params): Type
    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Type) -> Unit = {}
    ){
        val backgroundJob = scope.async {
            run(params)
        }
        scope.launch { onResult(backgroundJob.await()) }
    }
}