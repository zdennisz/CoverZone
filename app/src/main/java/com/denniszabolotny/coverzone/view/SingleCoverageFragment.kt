package com.denniszabolotny.coverzone.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.adapters.SingleCoverageRecyclerView
import com.denniszabolotny.coverzone.databinding.FragmentSingleCoverageBinding
import com.denniszabolotny.coverzone.db.CameraDatabase
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModel
import com.denniszabolotny.coverzone.viewModelFactorys.CamerasViewModelFactory
import com.denniszabolotny.coverzone.viewModelFactorys.SingleCameraViewModelFactory
import com.denniszabolotny.coverzone.viewmodel.ViewCoverageViewModel
import java.util.*


class SingleCoverageFragment : Fragment(), View.OnClickListener {
    private  var _binding: FragmentSingleCoverageBinding?=null
    private lateinit var  adapter:SingleCoverageRecyclerView
    private lateinit var addCameraViewModel:AddCameraViewModel
    private lateinit var viewCoverageViewModel:ViewCoverageViewModel

    private val binding get() = _binding!!

    var navController: NavController? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentSingleCoverageBinding.inflate(inflater,container,false)

        val dao= CameraDatabase.getInstace(inflater.context).cameraDAO
        val repository= CameraRepository(dao)
        val factory= CamerasViewModelFactory(repository)
        val secondFactory=SingleCameraViewModelFactory()
        //view model for all the cameras
        addCameraViewModel= ViewModelProvider(this,factory).get(AddCameraViewModel::class.java)
        //view model for the selected camera

        viewCoverageViewModel=ViewModelProvider(requireActivity(),secondFactory).get(ViewCoverageViewModel::class.java)
        binding.singleCoverageViewModel=addCameraViewModel
        binding.currentCameraViewModel=viewCoverageViewModel

        initRecyclerView(inflater.context)

        binding.lifecycleOwner=viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnAddCamera.setOnClickListener(this)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var newQuery = newText?.toLowerCase(Locale.ROOT)
                if (newQuery != null) {
                    binding.singleCoverageAdapter!!.filter.filter(newQuery)
                }
                return true
            }

        })

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
    private fun initRecyclerView(context: Context) {
        adapter=SingleCoverageRecyclerView()
        binding.singleCoverageAdapter=adapter
        binding.camerasRecyclerView.addItemDecoration( DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        displayRecyclerViewData()


    }

    private fun displayRecyclerViewData() {
        addCameraViewModel.cameras.observe(viewLifecycleOwner, Observer {
            binding.singleCoverageAdapter?.loadData(it as MutableList<Camera>,{ selectedItem: Camera -> itemClicked(selectedItem) },{  selectedItem: Camera -> nextButtonClicked(selectedItem)})

        })
    }
    private fun itemClicked(camera: Camera) {
        //next button click navigation
        navController!!.navigate(R.id.action_singleCoverageFragment_to_editCameraFragment)
    }

    private  fun nextButtonClicked(camera:Camera){
        //load the selected camera to operate on
        binding.currentCameraViewModel?.loadCamera(camera)
        //next button click navigation
        navController!!.navigate(R.id.action_singleCoverageFragment_to_viewCoverageFragment)

    }

}


