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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.adapters.SingleCoverageRecyclerView
import com.denniszabolotny.coverzone.databinding.FragmentSingleCoverageBinding
import com.denniszabolotny.coverzone.db.CameraDatabase
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModel
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModelFactory


class SingleCoverageFragment : Fragment(), View.OnClickListener {
    private  var _binding: FragmentSingleCoverageBinding?=null
    private lateinit var  adapter:SingleCoverageRecyclerView
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
                var newQuery = newText?.toLowerCase()
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
            binding.singleCoverageAdapter?.loadData(it as MutableList<Camera>)
        //binding.camerasRecyclerView = SingleCoverageRecyclerView(it, { selectedItem: Camera -> listItemClicked(selectedItem) })
        })
    }
    private fun listItemClicked(camera: Camera) {
        Toast.makeText(binding.view.context, "Selected camera name is ${camera.camera_name}", Toast.LENGTH_SHORT).show()
       // cameraViewModel.initUpdateAndDelete(camera)
    }

}


