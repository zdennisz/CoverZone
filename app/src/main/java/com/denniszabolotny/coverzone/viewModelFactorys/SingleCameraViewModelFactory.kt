package com.denniszabolotny.coverzone.viewModelFactorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denniszabolotny.coverzone.viewmodel.SharedViewCoverageViewModel



@Suppress("UNCHECKED_CAST")
class SingleCameraViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SharedViewCoverageViewModel::class.java)){
            return SharedViewCoverageViewModel() as T
        }
        throw IllegalArgumentException("unknown View Model Class")
    }
}