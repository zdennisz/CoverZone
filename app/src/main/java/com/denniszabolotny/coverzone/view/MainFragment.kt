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


class MainFragment : Fragment(),View.OnClickListener {

    var navController:NavController?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_Settings).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_multipleCameraCoverage).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_singleCameraCoverage).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       when(v!!.id){
           R.id.btn_singleCameraCoverage->navController!!.navigate(R.id.action_mainFragment_to_singleCameraCoverageFragment)
           R.id.btn_multipleCameraCoverage->navController!!.navigate(R.id.action_mainFragment_to_selectZoneFragment)
           R.id.btn_Settings->navController!!.navigate(R.id.action_mainFragment_to_settingsFragment)
       }
    }


}