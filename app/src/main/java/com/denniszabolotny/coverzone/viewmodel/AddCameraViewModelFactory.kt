package com.denniszabolotny.coverzone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denniszabolotny.coverzone.db.CameraRepository

class AddCameraViewModelFactory(private val repository: CameraRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCameraViewModel::class.java)) {
            return AddCameraViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}