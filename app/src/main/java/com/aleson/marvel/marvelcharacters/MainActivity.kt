package com.aleson.marvel.marvelcharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import br.com.connector.aleson.android.connector.Connector
import com.google.android.material.internal.ContextUtils.getActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Connector.init("https://gateway.marvel.com")
        setContentView(R.layout.activity_main)
    }
}