package com.denniszabolotny.coverzone.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.denniszabolotny.coverzone.R
import com.denniszabolotny.coverzone.databinding.ItemLayoutRecyclerViewBinding
import com.denniszabolotny.coverzone.models.Camera
import com.google.android.material.snackbar.Snackbar
import java.util.*

class SingleCoverageRecyclerView() :
    RecyclerView.Adapter<MyViewHolder>(), Filterable {
    private var dataFromView:MutableList<Camera>?=null
    private var cameraFilteredList:MutableList<Camera>?=null
    private var _onClick: ((Camera) -> Unit?)? =null
    private var _nextButtonClicked:((Camera)->Unit?)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ItemLayoutRecyclerViewBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_layout_recycler_view, parent, false)

        return MyViewHolder(binding, _onClick as (Camera) -> Unit,_nextButtonClicked as (Camera)->Unit)
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
                @Suppress("UNCHECKED_CAST")
                when(results!!){
                cameraFilteredList -> {
                    results?.values as MutableList<Camera>
                    notifyDataSetChanged()
                }

                else -> cameraFilteredList=dataFromView
                }

            }

        }
    }

    fun loadData(cameras:MutableList<Camera>,onClick: (Camera) -> Unit,nextButtonClicked:(Camera)->Unit){
        dataFromView=cameras
        cameraFilteredList=cameras
        _onClick=onClick
        _nextButtonClicked=nextButtonClicked
        notifyDataSetChanged()
    }

}


class MyViewHolder(val binding: ItemLayoutRecyclerViewBinding,val onClick:(Camera)->Unit,val nextButtonClicked:(Camera)->Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: Camera) {
        binding.camera=camera
            binding.imageButton.setOnClickListener{
                nextButtonClicked(camera)
            }
            binding.listItemLayout.setOnClickListener {
                onClick(camera)

       }

    }
}