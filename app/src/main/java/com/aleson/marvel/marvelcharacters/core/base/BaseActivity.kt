package com.aleson.marvel.marvelcharacters.core.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){


    protected abstract fun getFragmentContainer(): Int

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
    }
}