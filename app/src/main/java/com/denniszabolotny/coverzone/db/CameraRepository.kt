package com.denniszabolotny.coverzone.db

import com.denniszabolotny.coverzone.models.Camera

class CameraRepository(private val dao:CameraDAO ){

    val cameras=dao.getAllCameras()

    /*
    * Inserts a single camera
    * */
    suspend fun insert(camera: Camera){
        dao.insertCamera(camera)
    }

    /*
    * Updates a single camera
    * */
    suspend fun update(camera: Camera){
        dao.updateCamera(camera)
    }
    /*
    * Delete a single camera
    * */
    suspend fun delete(camera: Camera){
        dao.deleteCamera(camera)
    }
    /*
    * Deletes all the cameras
    * */
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}