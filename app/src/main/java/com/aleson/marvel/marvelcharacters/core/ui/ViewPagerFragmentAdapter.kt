package com.aleson.marvel.marvelcharacters.core.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.CharactersFragment
import com.aleson.marvel.marvelcharacters.feature.favorite.FavoritesFragment


class ViewPagerFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val arrayList: ArrayList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharactersFragment()
            else -> FavoritesFragment()
        }
    }

    fun add(fragment: Fragment) {
        this.arrayList.add(fragment)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}