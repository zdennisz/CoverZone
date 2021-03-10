package com.denniszabolotny.coverzone.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denniszabolotny.coverzone.models.Camera

class ViewCoverageViewModel: ViewModel(),Observable {

     val cameraToShow:LiveData<Camera>
        get()=_cameraToShow

     private val _cameraToShow=MutableLiveData<Camera>()

     fun loadCamera(camera: Camera){
         _cameraToShow.value=camera
     }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}