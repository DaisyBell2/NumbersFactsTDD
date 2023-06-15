package com.daisybell.numbersfactstdd.numbers.domain

import com.daisybell.numbersfactstdd.R
import com.daisybell.numbersfactstdd.numbers.presentation.ManageResources

/**
 * @author DaisyBell on 15.06.2023
 */
interface HandleError {

    fun handle(e: Exception): String

    class Base(private val manageResources: ManageResources) : HandleError {

        override fun handle(e: Exception) = manageResources.string(
            when (e) {
                is NoInternetConnectionException -> R.string.no_connection_message
                else -> R.string.service_is_unavailable
            }
        )

    }
}