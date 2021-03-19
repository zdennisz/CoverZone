package com.denniszabolotny.coverzone.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denniszabolotny.coverzone.models.Camera

class SharedViewCoverageViewModel: ViewModel(),Observable {

    private var _cameraToShow=MutableLiveData<Camera>()
    val cameraToShow:LiveData<Camera>
        get()
        {
            return _cameraToShow
        }


    fun loadCamera(camera: Camera){
         _cameraToShow.value=camera
     }

    fun getCamera():LiveData<Camera>{
        return cameraToShow
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}