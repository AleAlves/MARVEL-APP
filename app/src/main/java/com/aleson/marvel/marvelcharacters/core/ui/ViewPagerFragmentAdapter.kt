package com.aleson.marvel.marvelcharacters.core.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment.CharactersFragment
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment.FavoritesFragment


class ViewPagerFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val arrayList: ArrayList<BaseFragment> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharactersFragment()
            else -> FavoritesFragment()
        }
    }

    fun add(fragment: BaseFragment) {
        this.arrayList.add(fragment)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun getItem(): ArrayList<BaseFragment> {
        return arrayList
    }
}