package com.aleson.marvel.marvelcharacters.core.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.aleson.marvel.marvelcharacters.core.model.character.Character

abstract class ItemViewLayout(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    abstract fun onRefresh()

    abstract fun onFavorite(character: Character)
}