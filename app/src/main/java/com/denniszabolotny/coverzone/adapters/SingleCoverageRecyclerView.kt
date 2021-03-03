package com.denniszabolotny.coverzone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.ListItemBinding
import com.denniszabolotny.coverzone.models.Camera
import java.util.*

class SingleCoverageRecyclerView() :
    RecyclerView.Adapter<MyViewHolder>(), Filterable {
    private var dataFromView:MutableList<Camera>?=null
    private var cameraFilteredList:MutableList<Camera>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.list_item, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
            if(cameraFilteredList!=null){
                return cameraFilteredList!!.size
            }else{
                return 0
            }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(cameraFilteredList!=null){
            holder.bind(cameraFilteredList!![position])
        }
    }

     override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    cameraFilteredList = dataFromView!!
                } else {
                    var resultList = mutableListOf<Camera>()
                    for (row in cameraFilteredList!!) {
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
                cameraFilteredList = results?.values as MutableList<Camera>

                notifyDataSetChanged()
            }

        }
    }

    fun loadData(cameras:MutableList<Camera>){
        dataFromView=cameras
        cameraFilteredList=cameras
        notifyDataSetChanged()
    }

}


class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: Camera) {
        binding.camera=camera
      //      binding.listItemLayout.setOnClickListener {
            //clickListener(camera)
     //  }

    }
}