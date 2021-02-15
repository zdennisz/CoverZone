package com.denniszabolotny.coverzone.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.FragmentMainBinding


class MainFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    var navController: NavController? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnSettings.setOnClickListener(this)
        binding.btnMultipleCameraCoverage.setOnClickListener(this)
        binding.btnSingleCameraCoverage.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            binding.btnSingleCameraCoverage.id -> navController!!.navigate(R.id.action_mainFragment_to_singleCameraCoverageFragment)
            binding.btnMultipleCameraCoverage.id -> navController!!.navigate(R.id.action_mainFragment_to_selectZoneFragment)
            binding.btnSettings.id -> navController!!.navigate(R.id.action_mainFragment_to_settingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}