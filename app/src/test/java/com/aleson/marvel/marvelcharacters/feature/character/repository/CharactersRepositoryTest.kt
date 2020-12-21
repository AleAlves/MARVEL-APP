package com.aleson.marvel.marvelcharacters.feature.character.repository

import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSource
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class CharactersRepositoryTest {

    @Mock
    lateinit var dataSource: CharactersDataSource

    @Mock
    lateinit var getCharactersRequest: GetCharactersRequest

    @Mock
    lateinit var updateFavoriteRequest: UpdateFavoriteRequest

    @Mock
    lateinit var getFavoriteRequest: GetFavoriteRequest

    @Mock
    lateinit var onResponse: (Any) -> Unit

    @Mock
    lateinit var onError: (ErrorModel) -> Unit

    lateinit var repository: CharactersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CharactersRepository(dataSource)
    }

    @Test
    fun getCharacters() {
        repository.getCharacters(getCharactersRequest, onResponse, onError)
    }

    @Test
    fun updateFavorite() {
        repository.updateFavorite(updateFavoriteRequest, onResponse, onError)
    }

    @Test
    fun getFavoriteStatus() {
        repository.getFavoriteStatus(getFavoriteRequest, onResponse, onError)
    }

    @Test
    fun getFavorites() {
        repository.getFavorites(onResponse, onError)
    }
}