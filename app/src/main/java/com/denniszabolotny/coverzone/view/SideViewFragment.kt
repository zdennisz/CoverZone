package com.denniszabolotny.coverzone.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denniszabolotny.coverzone.R

/**
 * A simple [Fragment] subclass.
 * Use the [SideViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SideViewFragment : Fragment() {


    init{

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(Color.CYAN)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_side, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SideViewFragment()


    }
}