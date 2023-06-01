package com.daisybell.numbersfactstdd.numbers.presentation

import com.daisybell.numbersfactstdd.numbers.domain.NumberFact
import com.daisybell.numbersfactstdd.numbers.domain.NumbersResult

/**
 * @author DaisyBell on 01.06.2023
 */
class NumbersResultMapper(
    private val communications: NumbersCommunications,
    private val mapper: NumberFact.Mapper<NumberUi>
) : NumbersResult.Mapper<Unit> {

    override fun map(list: List<NumberFact>, errorMassage: String) = communications.showState(
        if (errorMassage.isEmpty()) {
            if (list.isNotEmpty())
                communications.showList(list.map { it.map(mapper) })
            UiState.Success()
        } else
            UiState.Error(errorMassage)
    )
}