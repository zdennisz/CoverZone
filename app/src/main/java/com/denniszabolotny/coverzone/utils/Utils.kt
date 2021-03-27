package com.denniszabolotny.coverzone.utils

import kotlin.math.PI
import kotlin.math.sqrt

object Utils {

    fun convertDegreeToRad(double:Double):Double{
        return (double * PI)/180
    }

    fun calcDetection(focalLength:Double,type: ObjectType,height:Double,width:Double,sensorPitch:Double):Double{
        return when(type){
            ObjectType.Object->calcTarget(focalLength, lpDetObj, objHeight, objWidth, sensorPitch)
            ObjectType.Human->calcTarget(focalLength, lpDet, humanHeight, humanWidth, sensorPitch)
            ObjectType.Nato->calcTarget(focalLength, lpDet, natoHeight, natoWidth, sensorPitch)
         }
    }

    fun calcRecognition(focalLength:Double,type: ObjectType,sensorPitch:Double):Double{
        return when(type){
            ObjectType.Object->calcTarget(focalLength, lpRec, objHeight, objWidth, sensorPitch)
            ObjectType.Human->calcTarget(focalLength, lpRec, humanHeight, humanWidth, sensorPitch)
            ObjectType.Nato->calcTarget(focalLength, lpRec, natoHeight, natoWidth, sensorPitch)
        }
    }

    fun calcIdentification(focalLength:Double,type: ObjectType,height:Double,width:Double,sensorPitch:Double):Double{
        return when(type){
            ObjectType.Nato->calcTarget(focalLength, lpIdent, natoHeight, natoWidth, sensorPitch)
            ObjectType.Human->calcTarget(focalLength, lpIdent, humanHeight, humanWidth, sensorPitch)
            else->0.0
            }
        }


    private fun calcTarget(focalLength:Double,line:Double,height:Double,width:Double,sensorPitch:Double):Double{
        return (focalLength/(line*sensorPitch/ OneMillion))*(sqrt(height*width)/ OneThousand)
    }
}