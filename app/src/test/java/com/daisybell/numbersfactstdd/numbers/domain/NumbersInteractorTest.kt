package com.daisybell.numbersfactstdd.numbers.domain

import com.daisybell.numbersfactstdd.numbers.presentation.ManageResources
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author DaisyBell on 02.06.2023
 */
class NumbersInteractorTest {

    private lateinit var interactor: NumbersInteractor
    private lateinit var repository: TestNumbersRepository
    private lateinit var manageResources: TestManageResources

    @Before
    fun init() {
        manageResources = TestManageResources()
        repository = TestNumbersRepository()
        interactor = NumbersInteractor.Base(
            repository,
            HandleRequest.Base(repository, HandleError.Base(manageResources))
        )
    }

    @Test
    fun `test init success`() = runBlocking {
        repository.changeExpectedList(listOf(NumberFact("6", "fact about 6")))
        val actual = interactor.init()

        val expected = NumbersResult.Success(listOf(NumberFact("6", "fact about 6")))

        assertEquals(expected, actual)
        assertEquals(1, repository.allNumbersCalledCount)

    }

    @Test
    fun `test fact about number success`() = runBlocking {
        // prepare data
        repository.changeExpectedFactOfNumbers(NumberFact("7", "fact about 7"))
        // action
        val actual = interactor.factAboutNumber("7")
        val expected = NumbersResult.Success(listOf(NumberFact("7", "fact about 7")))
        // check
        assertEquals(expected, actual)
        assertEquals(1, repository.numberFactCalledList.size)
        assertEquals("7", repository.numberFactCalledList[0])
    }

    @Test
    fun `test fact about number error`() = runBlocking {
        repository.expectedErrorGetFact(true)
        manageResources.changeExpected("no internet connection")

        val actual = interactor.factAboutNumber("7")
        val expected = NumbersResult.Failure("no internet connection")

        assertEquals(expected, actual)
        assertEquals(1, repository.numberFactCalledList.size)
        assertEquals("7", repository.numberFactCalledList[0])

    }

    @Test
    fun `test fact about random number success`() = runBlocking {
        // prepare data
        repository.changeExpectedFactOfRandomNumbers(NumberFact("7", "fact about 7"))
        // action
        val actual = interactor.factAboutRandomNumber()
        val expected = NumbersResult.Success(listOf(NumberFact("7", "fact about 7")))
        // check
        assertEquals(expected, actual)
        assertEquals(1, repository.randomNumberFactCalledList.size)
    }

    @Test
    fun `test fact about random number error`() = runBlocking {
        repository.expectedErrorGetRandomFact(true)
        manageResources.changeExpected("no internet connection")

        val actual = interactor.factAboutRandomNumber()
        val expected = NumbersResult.Failure("no internet connection")

        assertEquals(expected, actual)
        assertEquals(1, repository.randomNumberFactCalledList.size)

    }


    private class TestNumbersRepository : NumbersRepository {

        private val allNumbers = mutableListOf<NumberFact>()
        private var numberFact = NumberFact("", "")
        private var errorWhileNumberFact = false

        var allNumbersCalledCount = 0
        val numberFactCalledList = mutableListOf<String>()
        val randomNumberFactCalledList = mutableListOf<String>()

        fun changeExpectedList(list: List<NumberFact>) {
            allNumbers.clear()
            allNumbers.addAll(list)
        }

        fun changeExpectedFactOfNumbers(numberFact: NumberFact) {
            this.numberFact = numberFact
        }

        fun changeExpectedFactOfRandomNumbers(numberFact: NumberFact) {
            this.numberFact = numberFact
        }

        fun expectedErrorGetFact(error: Boolean) {
            errorWhileNumberFact = error
        }

        fun expectedErrorGetRandomFact(error: Boolean) {
            errorWhileNumberFact = error
        }

        override suspend fun allNumbers(): List<NumberFact> {
            allNumbersCalledCount++
            return allNumbers
        }

        override suspend fun numberFact(number: String): NumberFact {
            numberFactCalledList.add(number)
            if (errorWhileNumberFact)
                throw NoInternetConnectionException()
            allNumbers.add(numberFact)
            return numberFact
        }

        override suspend fun randomNumberFact(): NumberFact {
            randomNumberFactCalledList.add("")
            if (errorWhileNumberFact)
                throw NoInternetConnectionException()
            allNumbers.add(numberFact)
            return numberFact
        }

    }

    private class TestManageResources : ManageResources {

        private var value = ""
        fun changeExpected(string: String) {
            value = string
        }

        override fun string(id: Int): String = value

    }

}