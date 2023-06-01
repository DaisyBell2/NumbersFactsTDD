package com.daisybell.numbersfactstdd.numbers.presentation

/**
 * @author DaisyBell on 01.06.2023
 */
interface Mapper<R, S> {

    fun map(source: S): R

    interface Unit<S>: Mapper<kotlin.Unit, S>
}