package com.aleson.marvel.marvelcharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.connector.aleson.android.connector.Connector

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Connector.init("https://gateway.marvel.com")
        setContentView(R.layout.activity_main)
    }
}