package com.aleson.marvel.marvelcharacters.feature.character.view.ui

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.feature.character.model.Character
import com.aleson.marvel.marvelcharacters.feature.character.model.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ui.BaseRecyclerListener
import com.aleson.marvel.marvelcharacters.core.ui.BaseRecyclerViewAdapter
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.viewholder.CharacterViewHolder
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel

class CharactersFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: CharactersViewModel

    override fun getFragmentTag() = "CharactersFragment"

    override fun getFragmentLayout() = R.layout.fragment_characters

    override fun onBindView(view: View) {
        recyclerView = view.findViewById(R.id.characters_recyclerview)
    }

    override fun setupView() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    override fun setupViewModel() {
        this.viewModel = ViewModelProviders.of(
            this,
            CharactersInjector.provideCharactersViewModelFactory(activity?.applicationContext)
        ).get(CharactersViewModel::class.java)

        super.showLoading()
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
                is CharactersViewEvent.OnError -> super.showToast(context, it.toString())
            }
        })
    }

    private fun loadCharacters(characters: CharacterDataWrapper?) {
        super.hideLoading()
        adapter.clear()
        characters?.data.let {
            it?.results?.map { character ->
                adapter.add(character)
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun saveFavorite(character: Character) {
        viewModel.saveFavorite(character)
    }

    private var groupClickListener: BaseRecyclerListener<Character> =
        object : BaseRecyclerListener<Character> {

            override fun onClickListener(data: Character, v: View, position: Int) {

            }
        }

    private var adapter: BaseRecyclerViewAdapter<Character> =
        object : BaseRecyclerViewAdapter<Character>(groupClickListener) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                CharacterViewHolder(context, view) { saveFavorite(it) }

            override fun getLayoutId(position: Int, obj: Character) = R.layout.viewholder_character
        }
}