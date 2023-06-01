package com.daisybell.numbersfactstdd.numbers.presentation

import com.daisybell.numbersfactstdd.numbers.domain.NumberFact
import com.daisybell.numbersfactstdd.numbers.domain.NumberUiMapper
import org.junit.Assert.*
import org.junit.Test

/**
 * @author DaisyBell on 01.06.2023
 */
class NumbersResultMapperTest : BaseTest() {

    @Test
    fun `test error`() {
        val communication = TestNumbersCommunications()
        val mapper = NumbersResultMapper(communication, NumberUiMapper())

        mapper.map(emptyList(), "not empty message")

        assertEquals(UiState.Error("not empty message"), communication.stateCalledList[0])
    }

    @Test
    fun `test success no list`() {
        val communication = TestNumbersCommunications()
        val mapper = NumbersResultMapper(communication, NumberUiMapper())

        mapper.map(emptyList(), "")

        assertEquals(0, communication.timesShowList)
        assertEquals(true, communication.stateCalledList[0] is UiState.Success)
    }

    @Test
    fun `test success with list`() {
        val communication = TestNumbersCommunications()
        val mapper = NumbersResultMapper(communication, NumberUiMapper())

        mapper.map(listOf(NumberFact("75", "fact about 75")), "")

        assertEquals(1, communication.timesShowList)
        assertEquals(NumberUi("75", "fact about 75"), communication.numbersList[0])
        assertEquals(true, communication.stateCalledList[0] is UiState.Success)
    }

}