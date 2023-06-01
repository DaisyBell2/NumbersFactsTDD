package com.daisybell.numbersfactstdd.numbers.presentation

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author DaisyBell on 01.06.2023
 */
interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context) : ManageResources {
        override fun string(id: Int): String = context.getString(id)
    }

}