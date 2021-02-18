package com.denniszabolotny.coverzone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.FragmentAddCameraBinding

class AddCamera : Fragment(),View.OnClickListener {
    private var _binding: FragmentAddCameraBinding?=null
    private val binding get() = _binding!!
    var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAddCameraBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnCancelAction.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            binding.btnCancelAction.id->navController!!.navigate(R.id.action_addCamera_to_singleCoverageFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
}