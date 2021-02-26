package com.denniszabolotny.coverzone.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cameras_data_table")
data class Camera(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "camera_id")
        var id: Int,

        @ColumnInfo(name = "angle_offset")
        var angleOffset: String,

        @ColumnInfo(name = "focal_length")
        var focalLength: String,

        @ColumnInfo(name = "detector_width")
        var detector_width: String,

        @ColumnInfo(name = "detector_height")
        var detector_height: String,

        @ColumnInfo(name = "detector_pitch")
        var detector_pitch: String,

        @ColumnInfo(name="camera_height")
        var camera_height:String,

        @ColumnInfo(name="camera_name")
        var camera_name:String

)