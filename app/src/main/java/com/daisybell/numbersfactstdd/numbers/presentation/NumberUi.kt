package com.daisybell.numbersfactstdd.numbers.presentation

import android.widget.TextView

/**
 * @author DaisyBell on 01.06.2023
 */
data class NumberUi(
    private val id: String,
    private val fact: String
) {
    fun map(head: TextView, subTitle: TextView) {
        head.text = id
        subTitle.text = fact
    }

}