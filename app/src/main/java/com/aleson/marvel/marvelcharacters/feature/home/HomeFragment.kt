package com.aleson.marvel.marvelcharacters.feature.home

import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.aleson.marvel.marvelcharacters.core.ui.ViewPagerFragmentAdapter
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.CharactersFragment
import com.aleson.marvel.marvelcharacters.feature.favorite.FavoritesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentAdapter: ViewPagerFragmentAdapter

    override fun getFragmentTag(): String? = tag

    override fun getFragmentLayout() = R.layout.fragment_home

    override fun onBindView(view: View) {
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.home_viewpager)
        toolbar = view.findViewById(R.id.toolbar)
        toolbarIcon = view.findViewById(R.id.toolbar_image_button)
    }

    override fun setupView() {
        fragmentAdapter = ViewPagerFragmentAdapter(this)
        fragmentAdapter.add(CharactersFragment())
        fragmentAdapter.add(FavoritesFragment())
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.adapter = fragmentAdapter
        viewPager.isHorizontalScrollBarEnabled = true
        viewPager.isUserInputEnabled = true
        onSetupTabMediator()
        onChangeListener()
    }

    private fun onSetupTabMediator() {
        TabLayoutMediator(
            tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = getString(R.string.label_characters_header_title)
                    }
                    1 -> {
                        tab.text = getString(R.string.label_favorites_header_title)
                    }
                }
            }).attach()
    }

    private fun onChangeListener() {
        val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        toolbarIcon.setImageDrawable(context?.getDrawable(R.drawable.ic_iron_man_rounded))
                    }
                    1 -> {
                        toolbarIcon.setImageDrawable(context?.getDrawable(R.drawable.ic_heart_rounded))
                    }
                }
                super.onPageSelected(position)
            }
        }

        viewPager.registerOnPageChangeCallback(onPageChangeCallback)
    }

    override fun setupViewModel() {
    }

    override fun onBackPressed() {
    }

    override fun oberserverStates() {
    }

    override fun onClickListeners() {
    }

    override fun oberserverEvent() {
    }

}
