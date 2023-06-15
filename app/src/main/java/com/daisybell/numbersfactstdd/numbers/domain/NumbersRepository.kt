package com.daisybell.numbersfactstdd.numbers.domain

/**
 * @author DaisyBell on 02.06.2023
 */
interface NumbersRepository {

    suspend fun allNumbers(): List<NumberFact>

    suspend fun numberFact(number: String): NumberFact

    suspend fun randomNumberFact(): NumberFact
}