package com.aleson.marvel.marvelcharacters.feature.home

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.aleson.marvel.marvelcharacters.core.ui.ViewPagerFragmentAdapter
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.feature.character.CharactersFragment
import com.aleson.marvel.marvelcharacters.feature.favorite.FavoritesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment() {

    private lateinit var toolbarIcon : ImageView
    private lateinit var tabLayout: TabLayout
    private lateinit var myViewPager2: ViewPager2
    private lateinit var fragmentAdapter: ViewPagerFragmentAdapter

    override fun getFragmentTag(): String? = tag

    override fun getFragmentLayout() = R.layout.fragment_home

    override fun onBindView(view: View) {
        toolbarIcon = view.findViewById(R.id.toolbar_image_button)
        tabLayout = view.findViewById(R.id.tab_layout)
        myViewPager2 = view.findViewById(R.id.home_viewpager)
    }

    override fun setupView() {
        fragmentAdapter = ViewPagerFragmentAdapter(this)
        fragmentAdapter.add(CharactersFragment())
        fragmentAdapter.add(FavoritesFragment())
        myViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        myViewPager2.adapter = fragmentAdapter
        myViewPager2.isHorizontalScrollBarEnabled = true
        myViewPager2.isUserInputEnabled = true
        TabLayoutMediator(
            tabLayout, myViewPager2,
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

        myViewPager2.registerOnPageChangeCallback(onPageChangeCallback)
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
