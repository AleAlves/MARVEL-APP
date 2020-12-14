package com.aleson.marvel.marvelcharacters.core.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    var fragment: BaseFragment? = null

    protected abstract fun getFragmentContainer(): Int

    override fun onDestroy() {
        super.onDestroy()
        fragment = null
    }

    override fun onBackPressed() {
        fragment?.onBackPressed()
    }
}