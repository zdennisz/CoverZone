package com.denniszabolotny.coverzone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.FragmentAddCameraBinding
import com.denniszabolotny.coverzone.db.CameraDatabase
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModel
import com.denniszabolotny.coverzone.viewModelFactorys.CamerasViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AddCamera : Fragment(),View.OnClickListener {
    private var _binding: FragmentAddCameraBinding?=null
    private lateinit var addCameraViewModel: AddCameraViewModel
    private val binding get() = _binding!!

    var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAddCameraBinding.inflate(inflater,container,false)

        val dao= CameraDatabase.getInstace(inflater.context).cameraDAO
        val repository= CameraRepository(dao)
        val factory= CamerasViewModelFactory(repository)
        addCameraViewModel= ViewModelProvider(this,factory).get(AddCameraViewModel::class.java)
        binding.addCameraViewModel=addCameraViewModel
        binding.lifecycleOwner=viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnCancelAction.setOnClickListener(this)
        binding.btnAddCamera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            binding.btnCancelAction.id->navController!!.navigate(R.id.action_addCamera_to_singleCoverageFragment)
            binding.btnAddCamera.id -> {
                when(binding.addCameraViewModel!!.saveNewCamera()){
                    true->{
                        Snackbar.make(v,"Camera was added successfully",Snackbar.LENGTH_SHORT).show()
                        navController!!.navigate(R.id.action_addCamera_to_singleCoverageFragment)
                    }
                    false->{
                        Snackbar.make(v,"Please fill all the fields",Snackbar.LENGTH_SHORT).show()
                    }
                }

                hideKeyboard(v)

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
    fun hideKeyboard(v:View){
        val inputMethodManager=v.context.getSystemService<InputMethodManager>()
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(v.windowToken,0)
        }
}

}