package com.denniszabolotny.coverzone.viewmodel

import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.models.Camera
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AddCameraViewModel(private val repository: CameraRepository) : ViewModel(), Observable {

    val cameras = repository.cameras
    private var isUpdateOrDelte = false
    private lateinit var cameraToUpdateOrDelete: Camera

    @Bindable
    val cameraName=MutableLiveData<String>()
    @Bindable
    val offset = MutableLiveData<String>()

    @Bindable
    val detectorWidth = MutableLiveData<String>()

    @Bindable
    val detectorHeight = MutableLiveData<String>()

    @Bindable
    val focalLength = MutableLiveData<String>()

    @Bindable
    val detectorPitch = MutableLiveData<String>()

    @Bindable
    val cameraHeight= MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeletAllButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeletAllButtonText.value = "Clear all"
    }

    fun saveNewCamera(){
        val offsetText: String
        val detectorWidthText: String
        val detectorHeightText: String
        val detectorPitchText: String
        val focalLengthText: String
        val cameraHeightText:String
        val cameraNameText:String

        if (offset.value == null) {
            offsetText = ""
        } else {
            offsetText = offset.value!!
        }

        if (detectorWidth.value == null) {
            detectorWidthText = ""
        } else {
            detectorWidthText = detectorWidth.value!!
        }
        if (detectorHeight.value == null) {
            detectorHeightText = ""
        } else {
            detectorHeightText = detectorHeight.value!!
        }
        if (detectorPitch.value == null) {
            detectorPitchText = ""
        } else {
            detectorPitchText = detectorPitch.value!!
        }
        if (focalLength.value == null) {
            focalLengthText = ""
        } else {
            focalLengthText = focalLength.value!!
        }
        if (cameraHeight.value == null) {
            cameraHeightText = ""
        } else {
            cameraHeightText = cameraHeight.value!!
        }

        if (cameraName.value == null) {
            cameraNameText = ""
        } else {
            cameraNameText = cameraHeight.value!!
        }
        insert(Camera(0, offsetText, focalLengthText, detectorWidthText, detectorHeightText, detectorPitchText,cameraHeightText,cameraNameText))


        offset.value = null
        detectorWidth.value = null
        detectorHeight.value = null
        detectorPitch.value = null
        focalLength.value = null
        cameraHeight.value=null
        cameraName.value=null

    }

//    fun saveOrUpdate() {
//
//        if (isUpdateOrDelte) {
//            cameraToUpdateOrDelete.angleOffset = offset.value!!
//            cameraToUpdateOrDelete.detector_pitch = detectorPitch.value!!
//            cameraToUpdateOrDelete.detector_height = detectorHeight.value!!
//            cameraToUpdateOrDelete.detector_width = detectorWidth.value!!
//            cameraToUpdateOrDelete.focalLength = focalLength.value!!
//            update(cameraToUpdateOrDelete)
//        } else {
//            val offsetText: String
//            val detectorWidthText: String
//            val detectorHeightText: String
//            val detectorPitchText: String
//            val focalLengthText: String
//
//            if (offset.value == null) {
//                offsetText = ""
//            } else {
//                offsetText = offset.value!!
//            }
//
//            if (detectorWidth.value == null) {
//                detectorWidthText = ""
//            } else {
//                detectorWidthText = detectorWidth.value!!
//            }
//            if (detectorHeight.value == null) {
//                detectorHeightText = ""
//            } else {
//                detectorHeightText = detectorHeight.value!!
//            }
//            if (detectorPitch.value == null) {
//                detectorPitchText = ""
//            } else {
//                detectorPitchText = detectorPitch.value!!
//            }
//            if (focalLength.value == null) {
//                focalLengthText = ""
//            } else {
//                focalLengthText = focalLength.value!!
//            }
//
//            insert(Camera(0, offsetText, focalLengthText, detectorWidthText, detectorHeightText, detectorPitchText))
//
//            offset.value = null
//            detectorWidth.value = null
//            detectorHeight.value = null
//            detectorPitch.value = null
//            focalLength.value = null
//        }
//    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelte) {
            delete(cameraToUpdateOrDelete)
        } else {
            clearAll()
        }

    }

    fun insert(camera: Camera) {
        viewModelScope.launch {
            repository.insert(camera)
        }
    }

    fun update(camera: Camera) {
        viewModelScope.launch {
            repository.update(camera)
        }

    }

    fun delete(camera: Camera) {
        viewModelScope.launch {
            repository.delete(camera)
        }
        offset.value = null
        detectorHeight.value = null
        detectorWidth.value = null
        detectorPitch.value = null
        focalLength.value = null
        isUpdateOrDelte = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeletAllButtonText.value = "Clear All"
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun initUpdateAndDelete(camera: Camera) {
        offset.value = camera.angleOffset
        detectorHeight.value = camera.detector_height
        detectorWidth.value = camera.detector_width
        detectorPitch.value = camera.detector_pitch
        focalLength.value = camera.focalLength
        isUpdateOrDelte = true
        cameraToUpdateOrDelete = camera
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeletAllButtonText.value = "Delete"
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}