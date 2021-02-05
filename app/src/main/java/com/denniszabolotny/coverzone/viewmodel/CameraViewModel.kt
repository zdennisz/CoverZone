package com.denniszabolotny.coverzone.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denniszabolotny.coverzone.db.CameraRepository
import com.denniszabolotny.coverzone.models.Camera
import kotlinx.coroutines.launch

class CameraViewModel(private val repository: CameraRepository) : ViewModel(),Observable {

    val cameras=repository.cameras


    @Bindable
    val offset=MutableLiveData<String>()
    @Bindable
    val detectorWidth=MutableLiveData<String>()
    @Bindable
    val detectorHeight=MutableLiveData<String>()
    @Bindable
    val focalLength=MutableLiveData<String>()
    @Bindable
    val detectorPitch=MutableLiveData<String>()
    @Bindable
    val saveOrUpdateButtonText=MutableLiveData<String>()
    @Bindable
    val clearAllOrDeletAllButtonText=MutableLiveData<String>()

    init{
       saveOrUpdateButtonText.value="Save"
        clearAllOrDeletAllButtonText.value="Clear all"
    }

    fun saveOrUpdate(){
        val offsetText=offset.value!!
        val detectorWidthText=detectorWidth.value!!
        val detectorHeightText=detectorHeight.value!!
        val detectorPitchText=detectorPitch.value!!
        val focalLengthText=focalLength.value!!

        insert(Camera(0,offsetText,focalLengthText,detectorWidthText,detectorHeightText,detectorPitchText))

        offset.value=null
        detectorWidth.value=null
        detectorHeight.value=null
        detectorPitch.value=null
        focalLength.value=null
    }

    fun clearAllOrDelete(){
        clearAll()
    }

    fun insert(camera: Camera){
        viewModelScope.launch {
            repository.insert(camera)
        }
    }

    fun update(camera: Camera){
        viewModelScope.launch {
            repository.update(camera)
         }
    }
    fun delete(camera: Camera){
        viewModelScope.launch {
            repository.delete(camera)
        }
    }
    fun clearAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}