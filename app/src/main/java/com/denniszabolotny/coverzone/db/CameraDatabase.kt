package com.denniszabolotny.coverzone.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denniszabolotny.coverzone.models.Camera

@Database(entities = [Camera::class], version = 1)
abstract class CameraDatabase : RoomDatabase() {

    abstract val cameraDAO: CameraDAO

    companion object {
        @Volatile
        private var INSTANCE: CameraDatabase? = null
        fun getInstace(context: Context): CameraDatabase {
            synchronized(this) {
                var instace = INSTANCE
                if (instace == null) {
                    instace = Room.databaseBuilder(
                            context.applicationContext, CameraDatabase::class.java, "cameras_data_database").build()
                }
                return instace
            }
        }
    }
}