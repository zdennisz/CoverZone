package com.denniszabolotny.coverzone.view


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.adapters.ViewPagerAdapter
import com.denniszabolotny.coverzone.databinding.FragmentViewCoverageBinding
import com.denniszabolotny.coverzone.viewModelFactorys.SingleCameraViewModelFactory
import com.denniszabolotny.coverzone.viewmodel.SharedViewCoverageViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.camera_layout_view_coverage.view.*
import kotlinx.android.synthetic.main.fragment_view_coverage.*
import kotlinx.android.synthetic.main.fragment_view_coverage.view.*


class ViewCoverageFragment : Fragment() {
    private var _binding:FragmentViewCoverageBinding?=null
    private lateinit var viewModelShared:SharedViewCoverageViewModel
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
        viewModelShared=ViewModelProvider(requireActivity(),factory).get(SharedViewCoverageViewModel::class.java)
        binding.viewCoverageViewModel=viewModelShared
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
        binding.viewPager.adapter=ViewPagerAdapter()
        TabLayoutMediator(binding.tabLayout,binding.viewPager,object: TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
               //style each tab

                when(position){
                    0-> {
                        tab.text = "Side"
//                        tab.icon= R.drawable.img_title_addcamera as Drawable
                    }
                    1->{
                        tab.text="Top"
//                        tab.icon=R.drawable.img_multiple_coverage_main_screen as Drawable
                    }

                }

            }
        }).attach()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        viewModelShared.getCamera().let {
            binding.camera=it.value
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}