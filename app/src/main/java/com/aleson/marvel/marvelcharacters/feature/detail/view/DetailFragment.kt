package com.aleson.marvel.marvelcharacters.feature.detail.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.ui.BaseRecyclerViewAdapter
import com.aleson.marvel.marvelcharacters.core.util.loadImageFromUrl
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.Comics
import com.aleson.marvel.marvelcharacters.core.model.character.ComicsItem
import com.aleson.marvel.marvelcharacters.core.util.getIdfromURI
import com.aleson.marvel.marvelcharacters.feature.detail.di.DetailsInjector
import com.aleson.marvel.marvelcharacters.feature.detail.viewmodel.DetailsViewModel

class DetailFragment : BaseFragment() {

    private var character: Character? = null
    private lateinit var image: ImageView
    private lateinit var description: TextView
    private lateinit var descriptionContainer: ConstraintLayout
    private lateinit var comicsRecyclerView: RecyclerView
    private lateinit var seriesRecyclerView: RecyclerView
    private var comics = MutableLiveData<List<ComicsItem>>()
    private lateinit var viewModel: DetailsViewModel

    override fun getFragmentTag() = "CharactersDetailFragment"

    override fun getFragmentLayout(): Int = R.layout.fragment_detail

    override fun onBindView(view: View) {
        image = view.findViewById(R.id.character_details_imageview)
        description = view.findViewById(R.id.character_details_textview_description)
        descriptionContainer = view.findViewById(R.id.character_details_contraintlayout)
        toolbar = view.findViewById(R.id.toolbar)
        toolbarIcon = view.findViewById(R.id.toolbar_image_button)
        comicsRecyclerView = view.findViewById(R.id.comics_recyclerview)
        comicsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        comicsRecyclerView.adapter = comiscAdapter
    }

    override fun setupView() {
        character = arguments?.getParcelable("character")
        context?.let { loadImageFromUrl(it, character?.thumbnail, image, R.drawable.placeholder) }
        description.text = character?.description
        toolbar.title = character?.name
        toolbarIcon.visibility = View.GONE
        if (character?.description.isNullOrEmpty()) descriptionContainer.visibility = View.GONE
        character?.comics?.let { loadComics(it) }
    }

    private fun loadComics(comics: Comics) {
        comiscAdapter.clear()

        this.comics.value = comics.items

        this.comics.value?.map { item ->
            comiscAdapter.add(item)
            viewModel.getComicsMedia(getIdfromURI(item.resourceURI)){
                item.image = it
                comiscAdapter.notifyDataSetChanged()
            }
        }
        comiscAdapter.notifyDataSetChanged()
    }

    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            DetailsInjector.provideDetailsViewModelFactory(activity?.applicationContext)
        ).get(DetailsViewModel::class.java)
    }

    override fun onBackPressed() {
    }

    override fun oberserverStates() {
    }

    override fun onClickListeners() {
    }

    override fun oberserverEvent() {
    }

    private var comiscAdapter: BaseRecyclerViewAdapter<ComicsItem> =
        object : BaseRecyclerViewAdapter<ComicsItem>() {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                DetailsViewHolder(
                    context,
                    view
                )

            override fun getLayoutId(position: Int, obj: ComicsItem) = R.layout.viewholder_comics
        }
}