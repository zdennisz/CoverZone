package com.denniszabolotny.coverzone.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.denniszabolotny.coverzone.view.SideViewFragment
import com.denniszabolotny.coverzone.view.TopViewFragment

class ViewPagerAdapter(fa:FragmentActivity) : FragmentStateAdapter(fa) {


    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> SideViewFragment.newInstance()
            else -> TopViewFragment.newInstance()
        }
    }


}
