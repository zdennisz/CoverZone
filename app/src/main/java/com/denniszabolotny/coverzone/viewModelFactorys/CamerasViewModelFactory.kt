package com.denniszabolotny.coverzone.viewModelFactorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.viewmodel.AddCameraViewModel


@Suppress("UNCHECKED_CAST")
class CamerasViewModelFactory(private val repository: CameraRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCameraViewModel::class.java)) {
            return AddCameraViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}