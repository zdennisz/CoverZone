package com.denniszabolotny.coverzone.viewModelFactorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.viewmodel.ViewCoverageViewModel



@Suppress("UNCHECKED_CAST")
class SingleCameraViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewCoverageViewModel::class.java)){
            return ViewCoverageViewModel() as T
        }
        throw IllegalArgumentException("unknown View Model Class")
    }
}