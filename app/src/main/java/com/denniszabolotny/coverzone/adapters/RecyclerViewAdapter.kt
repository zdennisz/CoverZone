package com.denniszabolotny.coverzone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.ListItemBinding
import com.denniszabolotny.coverzone.models.Camera

class RecyclerViewAdapter(private val cameraList: List<Camera>, private val clickListener: (Camera) -> Unit) :
        RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.list_item, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cameraList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cameraList[position], clickListener)
    }

}


class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: Camera, clickListener: (Camera) -> Unit) {
        binding.tvDetectorHeight.text = camera.detector_height
        binding.tvDetectorWidth.text = camera.detector_width
        binding.tvDetectorPitch.text = camera.detector_pitch
        binding.tvFocalLength.text = camera.focalLength
        binding.tvOffset.text = camera.angleOffset
        binding.tvCameraName.text=camera.camera_name
        binding.tvCameraHeight.text=camera.camera_height
        binding.listItemLayout.setOnClickListener {
            clickListener(camera)
        }
    }
}