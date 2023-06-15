package com.daisybell.numbersfactstdd.numbers.domain

interface HandleRequest {

    suspend fun handle(block: suspend () -> Unit): NumbersResult

    class Base(
        private val repository: NumbersRepository,
        private val handleError: HandleError
    ) : HandleRequest {
        override suspend fun handle(block: suspend () -> Unit) = try {
            block.invoke()
            NumbersResult.Success(repository.allNumbers())
        } catch (e: Exception) {
            NumbersResult.Failure(handleError.handle(e))
        }

    }
}