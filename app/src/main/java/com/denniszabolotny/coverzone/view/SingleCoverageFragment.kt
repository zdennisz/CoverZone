package com.denniszabolotny.coverzone.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.adapters.RecyclerViewAdapter
import com.denniszabolotny.coverzone.databinding.FragmentSingleCoverageBinding
import com.denniszabolotny.coverzone.db.CameraDatabase
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModel
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModelFactory


class SingleCoverageFragment : Fragment(), View.OnClickListener {
    private  var _binding: FragmentSingleCoverageBinding?=null
    private lateinit var addCameraViewModel:AddCameraViewModel
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
        val factory= AddCameraViewModelFactory(repository)
        addCameraViewModel= ViewModelProvider(this,factory).get(AddCameraViewModel::class.java)
        binding.singleCoverageViewModel=addCameraViewModel
        binding.lifecycleOwner=viewLifecycleOwner
        initRecyclerView(inflater.context)
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
    private fun initRecyclerView(context: Context) {
        binding.camerasRecyclerView.layoutManager = LinearLayoutManager(context)
        displayCamerasList();

    }

    private fun displayCamerasList() {

        addCameraViewModel.cameras.observe(viewLifecycleOwner, Observer {
            binding.camerasRecyclerView.adapter = RecyclerViewAdapter(it, { selectedItem: Camera -> listItemClicked(selectedItem) })
        })
    }
    private fun listItemClicked(camera: Camera) {
        Toast.makeText(binding.camerasRecyclerView.context, "Selected camera name is ${camera.camera_name}", Toast.LENGTH_SHORT).show()
       // cameraViewModel.initUpdateAndDelete(camera)
    }

}