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
import com.denniszabolotny.coverzone.databinding.FragmentSingleCoverageBinding


class SingleCoverageFragment : Fragment(), View.OnClickListener {
    private  var _binding: FragmentSingleCoverageBinding?=null
    private val binding get() = _binding!!
    var navController: NavController? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentSingleCoverageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnAddCamera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
          binding.btnAddCamera.id -> navController!!.navigate(R.id.action_singleCoverageFragment_to_addCamera)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}