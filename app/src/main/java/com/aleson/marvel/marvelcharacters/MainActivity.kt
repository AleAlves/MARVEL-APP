package com.aleson.marvel.marvelcharacters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.detail.view.ui.fragment.DetailFragment
import com.aleson.marvel.marvelcharacters.feature.home.HomeFragment


class MainActivity : AppCompatActivity(), BaseFragment.OnFragmentEventsListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Connector.init("https://gateway.marvel.com")
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        navigateTo(HomeFragment())
    }

    override fun onDetails(character: Character) {
        navigateTo(DetailFragment.newInstance(character))
    }

    private fun navigateTo(fragment: Fragment) {
        this.fragment = fragment
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.base_fragment_container, this.fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(this.fragment)
        fragmentTransaction.commit()
    }
}