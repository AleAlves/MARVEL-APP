package com.aleson.marvel.marvelcharacters.feature.detail.view.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.Comics
import com.aleson.marvel.marvelcharacters.core.model.character.Series
import com.aleson.marvel.marvelcharacters.core.extension.getIdfromURI
import com.aleson.marvel.marvelcharacters.core.extension.loadImageFromUrl
import com.aleson.marvel.marvelcharacters.feature.detail.di.DetailsInjector
import com.aleson.marvel.marvelcharacters.feature.detail.view.event.DetailsViewEvent
import com.aleson.marvel.marvelcharacters.feature.detail.view.ui.custom.ResourceView
import com.aleson.marvel.marvelcharacters.feature.detail.viewmodel.DetailsViewModel

private const val ARG_CHARACTER = "character"

class DetailFragment : BaseFragment() {

    private var character: Character? = null
    private lateinit var image: ImageView
    private lateinit var description: TextView
    private lateinit var descriptionContainer: ConstraintLayout
    private lateinit var viewModel: DetailsViewModel
    private lateinit var comicsResourceView: ResourceView
    private lateinit var seriesResourceView: ResourceView

    override fun getFragmentTag() = "CharactersDetailFragment"

    override fun getFragmentLayout(): Int = R.layout.fragment_detail

    companion object {

        @JvmStatic
        fun newInstance(character: Character) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CHARACTER, character)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getParcelable(ARG_CHARACTER)
        }
    }

    override fun onBindView(view: View) {
        image = view.findViewById(R.id.character_details_imageview)
        description = view.findViewById(R.id.character_details_textview_description)
        descriptionContainer = view.findViewById(R.id.character_details_contraintlayout)
        toolbar = view.findViewById(R.id.toolbar)
        toolBarTitle = view.findViewById(R.id.toolbar_title)
        toolbarIcon = view.findViewById(R.id.toolbar_image_icon)
        comicsResourceView = view.findViewById(R.id.comics_recyclerview_custom)
        seriesResourceView = view.findViewById(R.id.series_recyclerview_custom)
        toolbarButton = view.findViewById(R.id.toolbar_imagebutton_delete)
    }

    override fun setupView() {
        character = arguments?.getParcelable("character")
        context?.let { loadImageFromUrl(it, character?.thumbnail, image, R.drawable.placeholder) }
        description.text = character?.description
        toolBarTitle.text = character?.name
        toolbarIcon.visibility = View.GONE
        toolbarButton.visibility = View.VISIBLE
        if (character?.description.isNullOrEmpty()) descriptionContainer.visibility = View.GONE
        character?.comics?.let { loadComics(it) }
        character?.series?.let { loadSeries(it) }
        comicsResourceView.setTitle(getString(R.string.label_details_title_comics))
        seriesResourceView.setTitle(getString(R.string.label_details_title_series))
        onSetFavoriteIconStatus(character?.favorite)
    }

    private fun loadComics(comics: Comics) {

        comics.items?.map { item ->
            viewModel.getComicsMedia(getIdfromURI(item.resourceURI)) {
                item.image = it
                comicsResourceView.notifyDataChange()
            }
        }
        comicsResourceView.addAll(comics.items)
    }

    private fun loadSeries(series: Series) {

        series.items?.map { item ->
            viewModel.getSeriesMedia(getIdfromURI(item.resourceURI)) {
                item.image = it
                seriesResourceView.notifyDataChange()
            }
        }

        seriesResourceView.addAll(series.items)
    }

    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            DetailsInjector.provideDetailsViewModelFactory(activity?.applicationContext)
        ).get(DetailsViewModel::class.java)
    }

    private fun onSetFavoriteIconStatus(operation: Boolean?) {
        if (character?.favorite as Boolean) {
            toolbarButton.setImageDrawable(context?.getDrawable(R.drawable.ic_baseline_favorite_24))
        } else {
            toolbarButton.setImageDrawable(context?.getDrawable(R.drawable.ic_baseline_favorite_border_24))
        }
    }

    override fun onClickListeners() {
        toolbarButton.setOnClickListener {
            character?.let { data -> viewModel.deleteFavorite(data) }
        }
    }

    override fun oberserverEvent() {
        this.viewModel.events.observe(this, Observer {
            when (it) {
                is DetailsViewEvent.OnFavoriteDeleted -> onSetFavoriteIconStatus(it.operation)
                is DetailsViewEvent.OnError -> super.showToast(context, it.toString())
            }
        })
    }
}