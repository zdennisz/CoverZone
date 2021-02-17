package com.denniszabolotny.coverzone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R


class SingleCoverageFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_coverage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_goToNextStep).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

          //  R.id.btn_goToNextStep -> navController!!.navigate(R.id.action_selectZoneFragment_to_placeCamerasFragment)
        }
    }


}