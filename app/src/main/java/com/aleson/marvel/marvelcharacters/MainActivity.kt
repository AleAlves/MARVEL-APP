package com.aleson.marvel.marvelcharacters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.API.Companion.baseUrl
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Connector.init(baseUrl)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}