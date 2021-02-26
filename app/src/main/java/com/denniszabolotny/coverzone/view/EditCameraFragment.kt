package com.denniszabolotny.coverzone.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.denniszabolotny.coverzone.adapters.RecyclerViewAdapter
import com.denniszabolotny.coverzone.databinding.FragmentEditCameraBinding
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModel


class EditCameraFragment : Fragment() {
    private var _binding: FragmentEditCameraBinding? = null
    private lateinit var cameraViewModel: AddCameraViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditCameraBinding.inflate(inflater, container, false)

     //   val dao = CameraDatabase.getInstace(inflater.context).cameraDAO
     //   val repository = CameraRepository(dao)
     //   val factory = CameraViewModelFactory(repository)
     //   cameraViewModel = ViewModelProvider(this, factory).get(CameraViewModel::class.java)
     //   binding.myViewModel = cameraViewModel
     //   binding.lifecycleOwner = viewLifecycleOwner
     //   initRecyclerView(inflater.context)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initRecyclerView(context: Context) {
        binding.camerasRecyclerView.layoutManager = LinearLayoutManager(context)
        displayCamerasList();

    }

    private fun displayCamerasList() {
        cameraViewModel.cameras.observe(viewLifecycleOwner, Observer {
            binding.camerasRecyclerView.adapter = RecyclerViewAdapter(it, { selectedItem: Camera -> listItemClicked(selectedItem) })
        })
    }

    private fun listItemClicked(camera: Camera) {
        Toast.makeText(binding.camerasRecyclerView.context, "Selected camera pitch is ${camera.detector_pitch}", Toast.LENGTH_SHORT).show()
        cameraViewModel.initUpdateAndDelete(camera)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}