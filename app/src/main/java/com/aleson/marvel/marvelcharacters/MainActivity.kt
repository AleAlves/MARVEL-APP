package com.aleson.marvel.marvelcharacters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.baseUrl

class MainActivity : AppCompatActivity(), BaseFragment.OnFragmentEventsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Connector.init(baseUrl)
        setContentView(R.layout.activity_main)
    }
}