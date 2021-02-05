package com.denniszabolotny.coverzone.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Cameras_data_table")
data class Camera(

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="camera_id")
    val id:Int,

    @ColumnInfo(name="angle_offset")
    val angleOffset:Double,

    @ColumnInfo(name="focal_length")
    val focalLength:Int,

    @ColumnInfo(name="detector_width")
    val detector_width:Int,

    @ColumnInfo(name="detector_height")
    val detector_height:Int,

    @ColumnInfo(name="detector_pitch")
    val detector_pitch:Double

)