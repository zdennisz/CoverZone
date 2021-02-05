package com.denniszabolotny.coverzone.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.denniszabolotny.coverzone.models.Camera

@Dao
interface CameraDAO {
    @Insert
    suspend fun insertCamera(camera:Camera):Long
    @Update
    suspend fun updateCamera(camera:Camera)
    @Delete
    suspend fun deleteCamera(camera: Camera)
    @Query("DELETE FROM Cameras_data_table")
    suspend fun deleteAll()
    @Query("SELECT * FROM Cameras_data_table")
    fun getAllCameras():List<LiveData<Camera>>
}