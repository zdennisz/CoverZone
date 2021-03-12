package com.denniszabolotny.coverzone.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.FragmentViewCoverageBinding
import com.denniszabolotny.coverzone.viewModelFactorys.SingleCameraViewModelFactory
import com.denniszabolotny.coverzone.viewmodel.ViewCoverageViewModel
import com.google.android.material.snackbar.Snackbar


class ViewCoverageFragment : Fragment() {
    private var _binding:FragmentViewCoverageBinding?=null
    private lateinit var viewModel:ViewCoverageViewModel
    private val binding get()=_binding!!
    private var boolean:Boolean=false

    var navController:NavController?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentViewCoverageBinding.inflate(inflater, container, false)


        val factory=SingleCameraViewModelFactory()
        viewModel=ViewModelProvider(this, factory).get(ViewCoverageViewModel::class.java)
        binding.viewCoverageViewModel=viewModel
        binding.expandButton.setOnClickListener {
            when(boolean){
                true->   {
                    binding.cardBottmHeight.setGuidelinePercent(0.90F)
                    boolean=!boolean
                }
                false-> {
                    binding.cardBottmHeight.setGuidelinePercent(0.5F)
                    boolean=!boolean
                }
            }



        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)

        //Snackbar.make(view,"Camera loaded is ${binding.viewCoverageViewModel!!.cameraToShow.value!!.camera_name}",Snackbar.LENGTH_SHORT).show()
        //val camera= Camera(0,"1","2","4","5","5","3","Dennis")
        //binding.viewCoverageViewModel.loadCamera(camera)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}