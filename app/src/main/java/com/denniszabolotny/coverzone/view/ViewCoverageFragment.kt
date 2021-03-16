package com.denniszabolotny.coverzone.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.FragmentViewCoverageBinding
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.viewModelFactorys.SingleCameraViewModelFactory
import com.denniszabolotny.coverzone.viewmodel.ViewCoverageViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.camera_layout_view_coverage.view.*
import kotlinx.android.synthetic.main.fragment_view_coverage.*
import kotlinx.android.synthetic.main.fragment_view_coverage.view.*


class ViewCoverageFragment : Fragment() {
    private var _binding:FragmentViewCoverageBinding?=null
    private lateinit var viewModel:ViewCoverageViewModel
    private val binding get()=_binding!!
    private var boolean:Boolean=false
    private var mBottomSheet: BottomSheetBehavior<View>?=null
    var navController:NavController?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentViewCoverageBinding.inflate(inflater, container, false)


        val factory=SingleCameraViewModelFactory()
        viewModel=ViewModelProvider(requireActivity(),factory).get(ViewCoverageViewModel::class.java)
        binding.viewCoverageViewModel=viewModel
        mBottomSheet=BottomSheetBehavior.from((binding.cameraBottomTab.cameraViewModelBind))
            binding.cameraBottomTab.cameraViewModelBind.setOnClickListener {
            when(boolean){
            true->   {
                mBottomSheet!!.state=BottomSheetBehavior.STATE_EXPANDED
                boolean=!boolean
            }
            false-> {
                mBottomSheet!!.state=BottomSheetBehavior.STATE_COLLAPSED
                boolean=!boolean
            }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        viewModel.getCamera().let {
           // binding.camera=it.value
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}