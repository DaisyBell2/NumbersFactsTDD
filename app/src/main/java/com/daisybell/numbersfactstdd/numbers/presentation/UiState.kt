package com.daisybell.numbersfactstdd.numbers.presentation


/**
 * @author DaisyBell on 01.06.2023
 */
sealed class UiState {

    // todo views on fragment

    class Success : UiState() {
    }

    data class Error(private val message: String) : UiState() {
    }
}