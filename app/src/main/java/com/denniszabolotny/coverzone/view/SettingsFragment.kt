package com.denniszabolotny.coverzone.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.FragmentSettingsBinding
import com.denniszabolotny.coverzone.db.CameraDatabase
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.viewmodel.CameraViewModel
import com.denniszabolotny.coverzone.viewmodel.CameraViewModelFactory


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding?=null
    private lateinit var cameraViewModel: CameraViewModel
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater,container,false)

        val dao=CameraDatabase.getInstace(inflater.context).cameraDAO
        val repository=CameraRepository(dao)
        val factory= CameraViewModelFactory(repository)
        cameraViewModel=ViewModelProvider(this,factory).get(CameraViewModel::class.java)
        binding.myViewModel=cameraViewModel
        binding.lifecycleOwner=viewLifecycleOwner
        displayCamerasList();
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun displayCamerasList(){
        cameraViewModel.cameras.observe(viewLifecycleOwner, Observer {
            Log.i("My Tag", it.toString())
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}