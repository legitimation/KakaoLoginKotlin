package com.example.kakaologinkotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    private val PAGENUMBER = 3

    override fun getItemCount(): Int {
        return PAGENUMBER
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when(position)
        {
            0->FirstFragment()
            1->SecondFragment()
            else->ThirdFragment()
        }
        return fragment
    }
}
