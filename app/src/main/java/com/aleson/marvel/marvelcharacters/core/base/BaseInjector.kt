package com.aleson.marvel.marvelcharacters.core.base

import android.content.Context
import androidx.room.Room
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.room
import com.aleson.marvel.marvelcharacters.core.room.dao.RoomLocalDataBase


abstract class BaseInjector {


    companion object {

        fun database(context: Context?) = context?.let { context ->
            Room.databaseBuilder(context, RoomLocalDataBase::class.java, room)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}