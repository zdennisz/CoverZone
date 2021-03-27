package com.denniszabolotny.coverzone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.denniszabolotny.coverzone.R

class ViewPagerAdapter : Adapter<ViewPagerAdapter.EventViewHolder>() {
val eventList=listOf(0,1)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.EventViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.tab_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.EventViewHolder, position: Int) {
        (holder.view as? TextView)?.also {
            it.text = "Page " + eventList[position]
        }
    }

    override fun getItemCount(): Int {
       return eventList.count()
    }

    class EventViewHolder(val view: View):RecyclerView.ViewHolder(view)
}
