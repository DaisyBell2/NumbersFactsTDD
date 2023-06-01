package com.daisybell.numbersfactstdd.numbers.domain

/**
 * @author DaisyBell on 01.06.2023
 */
interface NumbersInteractor {

    suspend fun init(): NumbersResult

    suspend fun factAboutNumber(number: String): NumbersResult

    suspend fun factAboutRandomNumber(): NumbersResult
}