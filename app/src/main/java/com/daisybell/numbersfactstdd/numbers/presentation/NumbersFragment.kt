package com.daisybell.numbersfactstdd.numbers.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.daisybell.numbersfactstdd.R
import com.daisybell.numbersfactstdd.details.presentation.DetailsFragment
import com.daisybell.numbersfactstdd.main.presentation.ShowFragment

/**
 * @author DaisyBell on 30.05.2023
 */
class NumbersFragment : Fragment() {

    private var showFragment: ShowFragment = ShowFragment.Empty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = context as ShowFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        // todo refactor and remove hardcode
        view.findViewById<View>(R.id.getFactButton).setOnClickListener {
            showFragment.show(DetailsFragment.newInstance("some information about the random number hardcoded"))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showFragment = ShowFragment.Empty()
    }

}