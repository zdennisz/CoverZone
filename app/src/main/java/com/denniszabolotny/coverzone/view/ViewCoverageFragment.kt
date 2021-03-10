package com.denniszabolotny.coverzone.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.FragmentViewCoverageBinding
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.viewModelFactorys.SingleCameraViewModelFactory
import com.denniszabolotny.coverzone.viewmodel.ViewCoverageViewModel


class ViewCoverageFragment : Fragment() {
    private var _binding:FragmentViewCoverageBinding?=null
    private lateinit var viewModel:ViewCoverageViewModel
    private val binding get()=_binding!!

    var navController:NavController?=null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentViewCoverageBinding.inflate(inflater,container,false)
        //get camera from prev. framgent

        val factory=SingleCameraViewModelFactory()
        viewModel=ViewModelProvider(this,factory).get(ViewCoverageViewModel::class.java)
        binding.viewCoverageViewModel=viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        val camera= Camera(0,"1","2","4","5","5","3","Dennis")
        //binding.viewCoverageViewModel.loadCamera(camera)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}