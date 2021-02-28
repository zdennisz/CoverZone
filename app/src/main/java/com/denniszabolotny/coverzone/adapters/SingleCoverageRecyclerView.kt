package com.denniszabolotny.coverzone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.ListItemBinding
import com.denniszabolotny.coverzone.models.Camera
import java.util.*

class SingleCoverageRecyclerView :
    RecyclerView.Adapter<MyViewHolder>, Filterable {

    private val cameraList: List<Camera>
    private val clickListener: (Camera) -> Unit
    private lateinit var cameraFilteredList:List<Camera>
    constructor(cameraList: List<Camera>, clickListener: (Camera) -> Unit) : super() {
        this.cameraList = cameraList
        this.clickListener = clickListener
        cameraFilteredList= this.cameraList.toList()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.list_item, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cameraFilteredList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cameraFilteredList[position], clickListener)
    }

     override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    cameraFilteredList = cameraList
                } else {
                    var resultList = mutableListOf<Camera>()
                    for (row in cameraFilteredList) {
                        if (row.camera_name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(
                                Locale.ROOT
                            )
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    cameraFilteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = cameraFilteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                cameraFilteredList = results?.values as List<Camera>
                notifyDataSetChanged()
            }

        }
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