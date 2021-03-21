package com.denniszabolotny.coverzone.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denniszabolotny.coverzone.models.Camera
import com.denniszabolotny.coverzone.utils.Nintey
import com.denniszabolotny.coverzone.utils.TwoThousand
import com.denniszabolotny.coverzone.utils.Utils.convertDegreeToRad
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.tan

class ViewCoverageViewModel: ViewModel() {


    //here is where we preform all the calculations for the top and the side view


     //At last we have to take all the zone and subtract the dead zone to get the zone that we can actually cover

    fun calculateZoneHfov(camera: Camera,hFov:Double):Double{
       val camAngle=camera.angleOffset.toDouble()-0.5*hFov
       val radians= convertDegreeToRad(camAngle)
        return camera.camera_height.toDouble() / tan(radians)
    }

    fun calculateDeadZone(height:Double,angle: Double):Double{
        val radians= convertDegreeToRad(angle)
        return height/(tan(radians))
    }
    fun calculateDRI(camera: Camera):MutableMap<String,Double>?{
        //TODO(preform the same calculations with the DRI calculator and return the result as a map)
       val res:MutableMap<String,Double>?=null


       return res
    }

    fun calculateHfov(sensoPitch:Double,dimenW:Double,focalLength:Double):Double{
        return 2*atan((sensoPitch * dimenW) /
                (TwoThousand * focalLength)) * (2* Nintey) / PI;
    }

    fun calculateVfov(dimenH:Double,dimenW:Double,Hfov:Double):Double{
        return Hfov*(dimenH/dimenW)
    }





}