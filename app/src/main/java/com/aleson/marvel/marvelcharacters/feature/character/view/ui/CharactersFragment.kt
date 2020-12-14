package com.aleson.marvel.marvelcharacters.feature.character.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.model.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel

class CharactersFragment : BaseFragment() {

    private lateinit var viewModel: CharactersViewModel

    override fun getFragmentTag() = "CharactersFragment"

    override fun getFragmentLayout() = R.layout.fragment_characters

    override fun onBindView(view: View) {
    }

    override fun setupView() {
    }

    override fun setupViewModel() {
        this.viewModel = ViewModelProviders.of(
            this,
            CharactersInjector.provideCharactersViewModelFactory()).get(CharactersViewModel::class.java)

        this.viewModel.setup()
    }

    override fun onBackPressed() {
    }

    override fun oberserverStates() {
    }

    override fun onClickListeners() {
    }

    override fun oberserverEvent() {
        this.viewModel.events.observe(this, Observer {
            when (it) {
                is CharactersViewEvent.OnLoadCharacters -> loadCharacters(it.characters)
            }
        })
    }

    private fun loadCharacters(characters: CharacterDataWrapper?) {
        print("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}