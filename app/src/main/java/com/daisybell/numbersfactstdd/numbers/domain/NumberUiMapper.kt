package com.daisybell.numbersfactstdd.numbers.domain

import com.daisybell.numbersfactstdd.numbers.presentation.NumberUi

class NumberUiMapper : NumberFact.Mapper<NumberUi> {
    override fun map(id: String, fact: String): NumberUi = NumberUi(id, fact)
}