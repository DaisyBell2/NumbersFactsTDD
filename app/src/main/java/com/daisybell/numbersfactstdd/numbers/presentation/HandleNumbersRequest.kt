package com.daisybell.numbersfactstdd.numbers.presentation

import com.daisybell.numbersfactstdd.numbers.domain.NumbersResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author DaisyBell on 01.06.2023
 */
interface HandleNumbersRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> NumbersResult
    )

    class Base(
        private val dispatchers: DispatchersList,
        private val communications: NumbersCommunications,
        private val numbersResultMapper: NumbersResult.Mapper<Unit>
    ) : HandleNumbersRequest {
        override fun handle(coroutineScope: CoroutineScope, block: suspend () -> NumbersResult) {
            communications.showProgress(true)
            coroutineScope.launch(dispatchers.io()) {
                val result = block.invoke()
                communications.showProgress(false)
                result.map(numbersResultMapper)
            }
        }

    }
}