package com.aleson.marvel.marvelcharacters.core.di

import android.content.Context
import androidx.room.Room
import com.aleson.marvel.marvelcharacters.core.dao.AppDatabase

abstract class BaseInjector {


    companion object {

        fun database(context: Context?) = context?.let { context ->
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}